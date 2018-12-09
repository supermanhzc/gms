package com.taoyuan.gms.core.proxymanage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.common.entity.TyPageEntity;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.proxy.RechargeWithdrawApi;
import com.taoyuan.gms.core.proxymanage.dao.RechargeWithdrawMapper;
import com.taoyuan.gms.core.proxymanage.service.IRechargeWithdrawService;
import com.taoyuan.gms.model.dto.BaseIdRequest;
import com.taoyuan.gms.model.dto.proxy.RechargeWithdrawRequest;
import com.taoyuan.gms.model.entity.proxy.RechargeWithdrawEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@RestController
public class RechargeWithdrawControllerGms extends BaseGmsProxyController implements RechargeWithdrawApi {

    @Autowired
    private IRechargeWithdrawService service;

    @Autowired
    private RechargeWithdrawMapper mapper;

    @Override
    public TyResponse getRechargeWithdraws(@RequestBody TyPageEntity entity) {
        Page page = getPage(entity);
        return new TySuccessResponse(mapper.selectPage(page, null));
    }

    @Override
    public TyResponse rechargeWithdraws(@RequestBody RechargeWithdrawRequest request) {
        int type = request.getType();
        if (type != 1 && type != 2) {
            throw new ValidateException("充值提现类型非法。");
        }

        if (request.getMoney() == null) {
            throw new ValidateException("充值或者提现金额不能为空。");
        }

        //提现时代理额度必须大于提现额度
        if (2 == type & getCurrentUserBalance().compareTo(request.getMoney()) < 0) {
            throw new ValidateException("用户余额小于提现额度。");
        }

        RechargeWithdrawEntity entity = new RechargeWithdrawEntity();
        BeanUtils.copyProperties(request, entity);
        entity.setCreateTime(new Date());
        entity.setProxyId(getCurrentUserId());
        entity.setProxyName(getCurrentUserName());
        //充值或者提现需要提交给管理员审批，当前只是设置状态为1，管理员处理后处理代理额度
        entity.setStatus(1);
        entity.setBalance(getBalance(getCurrentUserId()));
        entity.setDistribution(getBalance(getCurrentUserId()));
        service.save(entity);

        //记录日志
        recordOperation(type, null, request.getMoney());
        return new TySuccessResponse(entity);
    }

    @Override
    public TyResponse cancel(@RequestBody BaseIdRequest request) {
        Long id = request.getId();
        if (null == id) {
            throw new ValidateException("id不能为空。");
        }

        RechargeWithdrawEntity dbValue = service.getOne(id);
        if (dbValue.getStatus() != 1) {
            throw new ValidateException("只能取消未处理状态。");
        }
        //取消的话直接修改状态，余额不用变动
        dbValue.setStatus(3);
        dbValue.setFinishTime(new Date());
        service.saveOrUpdate(dbValue);
        return new TySuccessResponse(dbValue);
    }

    @Override
    public TyResponse process(@RequestBody BaseIdRequest request) {
        Long id = request.getId();
        if (null == id) {
            throw new ValidateException("id不能为空。");
        }

        RechargeWithdrawEntity dbValue = service.getOne(id);
        if (dbValue.getStatus() != 1) {
            throw new ValidateException("只能处理未处理状态。");
        }
        dbValue.setStatus(2);
        dbValue.setFinishTime(new Date());
        service.saveOrUpdate(dbValue);

        //代理余额处理，如果是充值则加上金额，如果是提现则减去金额,如果余额小于提现额则报错
        int type = dbValue.getType();
        if (type == 1) {
            updateBalance(id, dbValue.getMoney());
        } else if (type == 2) {
            if (getCurrentUserBalance().compareTo(dbValue.getMoney()) < 0) {
                throw new ValidateException("用户余额小于提现额度。");
            }
            updateBalance(id, BigDecimal.ZERO.subtract(dbValue.getMoney()));
        }
        return new TySuccessResponse(dbValue);
    }
}
