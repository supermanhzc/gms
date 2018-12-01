package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.framework.bs.controller.TyBaseController;
import com.taoyuan.framework.common.entity.TyUserRolePermission;
import com.taoyuan.framework.common.exception.TyBusinessException;
import com.taoyuan.framework.common.exception.TyExceptionUtil;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.admin.UserBalanceChangeApi;
import com.taoyuan.gms.core.adminmanage.service.IUserBalanceChangeService;
import com.taoyuan.gms.model.dto.admin.UserBalanceChangeDto;
import com.taoyuan.gms.model.dto.admin.account.QueryAccountBalanceRequest;
import com.taoyuan.gms.model.dto.admin.account.UpdateAccountBalanceRequest;
import com.taoyuan.gms.model.entity.admin.account.UserBalanceChangeEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
public class UserBalanceChangeController extends TyBaseController implements UserBalanceChangeApi{
    @Autowired
    private IUserBalanceChangeService uerBalanceChangeService;

    @Override
    public IPage<Map<String, Object>> getAllBalanceChangeHistory(QueryAccountBalanceRequest queryAccountBalanceRequest) {
        QueryWrapper wrapper = new QueryWrapper();
        if(null != queryAccountBalanceRequest.getId()){
//            wrapper.lambda().eq(UserBalanceChangeEntity::getId, queryAccountBalanceRequest.getId());
        }
        uerBalanceChangeService.page(this.getPage(queryAccountBalanceRequest), null);
        return null;
    }

    @Override
    public TyResponse changeBalance(UpdateAccountBalanceRequest updateAccountBalanceRequest) {
        if(uerBalanceChangeService.changeBalance(updateAccountBalanceRequest)){
            return new TySuccessResponse("user balance changed successfully.");
        }
        throw new TyBusinessException("user balance changed failed.");
    }
}
