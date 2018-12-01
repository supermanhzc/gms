package com.taoyuan.gms.core.proxymanage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.common.entity.TyPageEntity;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.proxy.RechargeWithdrawApi;
import com.taoyuan.gms.core.adminmanage.controller.BaseGmsController;
import com.taoyuan.gms.core.proxymanage.service.IRechargeWithdrawService;
import com.taoyuan.gms.model.entity.proxy.RechargeWithdrawEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class RechargeWithdrawControllerGms extends BaseGmsController implements RechargeWithdrawApi {

    @Autowired
    private IRechargeWithdrawService service;

    @Override
    public List<RechargeWithdrawEntity> getRechargeWithdraws(TyPageEntity entity) {
        Page page = getPage(entity);
        return service.pageMaps(page, null).getRecords();
    }

    @Override
    public TyResponse rechargeWithdraws(RechargeWithdrawEntity rechargeWithdrawEntity) {
        int type = rechargeWithdrawEntity.getType();
        if (type != 1 && type != 2) {
            throw new ValidateException("充值提现类型非法。");
        }

        if (rechargeWithdrawEntity.getMoney() == null) {
            throw new ValidateException("充值或者提现金额不能为空。");
        }

        Long proxyId = TySession.getCurrentUser().getUserId();
        rechargeWithdrawEntity.setCreateTime(new Date());
        rechargeWithdrawEntity.setProxyId(proxyId);
        rechargeWithdrawEntity.setStatus(1);
        rechargeWithdrawEntity.setBalance(getBalance(proxyId));
        rechargeWithdrawEntity.setDistribution(getBalance(proxyId));
        service.save(rechargeWithdrawEntity);
        return new TySuccessResponse(rechargeWithdrawEntity);
    }

    @Override
    public TyResponse cancel(RechargeWithdrawEntity rechargeWithdrawEntity) {
        Long id = rechargeWithdrawEntity.getId();
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
    public TyResponse process(RechargeWithdrawEntity rechargeWithdrawEntity) {
        Long id = rechargeWithdrawEntity.getId();
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
        int type = rechargeWithdrawEntity.getType();
        if (type == 1) {
            updateBalance(id, rechargeWithdrawEntity.getMoney());
        } else if (type == 2) {
            updateBalance(id, BigDecimal.ZERO.subtract(rechargeWithdrawEntity.getMoney()));
        }
        return new TySuccessResponse(dbValue);
    }
}
