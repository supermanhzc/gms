package com.taoyuan.gms.core.adminmanage.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.taoyuan.framework.bs.controller.TyBaseController;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.admin.UserGoldApi;
import com.taoyuan.gms.core.adminmanage.service.IUserGoldService;
import com.taoyuan.gms.model.dto.admin.account.GoldChangeRequest;

@RestController
public class UserGoldController extends TyBaseController implements UserGoldApi
{
    @Autowired
    private IUserGoldService goldService;
    
    @Override
    public TyResponse deduct(GoldChangeRequest request)
    {
        if(null==request.getId()){
            throw new ValidateException("用户id不能为空。");
        }

        if(null==request.getGold()||BigDecimal.ZERO.equals(request.getGold())){
            throw new ValidateException("金币不能为空。");
        }

        boolean rslt = goldService.deduct(request.getId(),request.getGold());
        return new TySuccessResponse(rslt);
    }
    
    @Override
    public TyResponse add(GoldChangeRequest request)
    {
        if(null==request.getId()){
            throw new ValidateException("用户id不能为空。");
        }

        if(null==request.getGold()||BigDecimal.ZERO.equals(request.getGold())){
            throw new ValidateException("金币不能为空。");
        }

        boolean rslt = goldService.add(request.getId(),request.getGold());
        return new TySuccessResponse(rslt);
    }
}
