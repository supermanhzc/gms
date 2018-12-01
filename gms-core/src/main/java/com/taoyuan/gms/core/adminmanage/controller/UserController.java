package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.framework.bs.controller.TyBaseController;
import com.taoyuan.framework.common.constant.ResultCode;
import com.taoyuan.framework.common.entity.TyPageEntity;
import com.taoyuan.framework.common.exception.TyExceptionUtil;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.admin.UserApi;
import com.taoyuan.gms.common.consts.UserTypeConsts;
import com.taoyuan.gms.core.adminmanage.service.IUserService;
import com.taoyuan.gms.model.dto.admin.account.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class UserController extends TyBaseController implements UserApi {

    @Autowired
    private IUserService userService;

    @Override
    public TyResponse queryUsers(QueryAccountRequest queryAccountRequest) {
        IPage result = userService.queryUsers(queryAccountRequest, getPage(queryAccountRequest));
        if(null != result){
            return new TySuccessResponse(result);
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_GET_ERROR);
    }

    @Override
    public TyResponse queryProxys(TyPageEntity queryProxyRequest) {
        IPage result = userService.queryProxys(getPage(queryProxyRequest));
        if(null != result){
            return new TySuccessResponse(result);
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_GET_ERROR);
    }

    @Override
    public TyResponse deleteUser(Long id) {
        if(userService.deleteUser(id)){
            return new TySuccessResponse("user delete successfully.");
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_REMOVE_ERROR);
    }

    @Override
    public TyResponse register(RegisterAccountRequest registerAccountRequest) {
        if(userService.createUser(UserTypeConsts.NORMALUSER, registerAccountRequest)){
            return new TySuccessResponse("user register successfully.");
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_REGISTRY_ERROR);
    }

    @Override
    public TyResponse update(UpdateAccountRequest updateAccountRequest) {
        if(userService.updateUser(updateAccountRequest)){
            return new TySuccessResponse("user update successfully.");
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_UPDATE_ERROR);
    }

    @Override
    public TyResponse allocateProxy(AllocateProxyRequest allocateProxyRequest) {
        if(userService.createUser(UserTypeConsts.PROXY, allocateProxyRequest)){
            return new TySuccessResponse("proxy allocate successfully.");
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_REGISTRY_ERROR);
    }

    @Override
    public TyResponse updateProxy(UpdateProxyRequest updateProxyRequest) {
        if(userService.updateUser(updateProxyRequest)){
            return new TySuccessResponse("proxy update successfully.");
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_UPDATE_ERROR);
    }

    @Override
    public TyResponse createAdmin(CreateAdminRequest createAdminRequest) {
        if(userService.createUser(UserTypeConsts.ADMIN, createAdminRequest)){
            return new TySuccessResponse("admin allocate successfully.");
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_REGISTRY_ERROR);
    }

    @Override
    public TyResponse updateAdmin(UpdateAdminRequest updateAdminRequest){
        if(userService.updateUser(updateAdminRequest)){
            return new TySuccessResponse("admin update successfully.");
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_UPDATE_ERROR);
    }

    @Override
    public void batchCreateUser(Integer sysUserCount) {

    }
}
