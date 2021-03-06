package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.framework.bs.aspect.OperControllerLog;
import com.taoyuan.framework.bs.controller.TyBaseController;
import com.taoyuan.framework.common.exception.TyBusinessException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.admin.UserBalanceChangeApi;
import com.taoyuan.gms.core.adminmanage.service.IUserBalanceChangeService;
import com.taoyuan.gms.model.dto.admin.account.QueryAccountBalanceRequest;
import com.taoyuan.gms.model.dto.admin.account.UpdateAccountBalanceRequest;
import com.taoyuan.gms.model.entity.admin.account.UserBalanceChangeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserBalanceChangeController extends TyBaseController implements UserBalanceChangeApi{
    @Autowired
    private IUserBalanceChangeService uerBalanceChangeService;

    @Override
    @OperControllerLog(module = "账号明细管理", type = "查询账号明细")
    public TyResponse getAllBalanceChangeHistory(@RequestBody QueryAccountBalanceRequest queryAccountBalanceRequest) {
        QueryWrapper<UserBalanceChangeEntity> wrapper = new QueryWrapper();
        if(null != queryAccountBalanceRequest.getId()){
            wrapper.lambda().eq(UserBalanceChangeEntity::getId, queryAccountBalanceRequest.getId());
        }
        if(null != queryAccountBalanceRequest.getType()){
            wrapper.lambda().eq(UserBalanceChangeEntity::getType, queryAccountBalanceRequest.getType());
        }
        IPage result = uerBalanceChangeService.page(this.getPage(queryAccountBalanceRequest), wrapper);
        if(null != result){
            return new TySuccessResponse(result);
        }
        throw new TyBusinessException("user balance get error.");
    }

    @Override
    @OperControllerLog(module = "账号明细管理", type = "修改余额")
    public TyResponse changeBalance(@RequestBody UpdateAccountBalanceRequest updateAccountBalanceRequest) {
        if(uerBalanceChangeService.changeBalance(updateAccountBalanceRequest)){
            return new TySuccessResponse("user balance changed successfully.");
        }
        throw new TyBusinessException("user balance changed failed.");
    }
}
