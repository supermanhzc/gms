package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.framework.bs.aspect.OperControllerLog;
import com.taoyuan.framework.bs.controller.TyBaseController;
import com.taoyuan.framework.common.constant.ResultCode;
import com.taoyuan.framework.common.entity.TyPageEntity;
import com.taoyuan.framework.common.exception.TyExceptionUtil;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.admin.UserApi;
import com.taoyuan.gms.common.consts.OperLogKeyConsts;
import com.taoyuan.gms.common.consts.UserTypeConsts;
import com.taoyuan.gms.core.adminmanage.service.IUserService;
import com.taoyuan.gms.model.dto.admin.account.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class UserController extends TyBaseController implements UserApi {

    @Autowired
    private IUserService userService;

    @Override
    @OperControllerLog(key = OperLogKeyConsts.GMS_QUERY_ADMINS_KEY, module = "用户管理", type = "查询管理员列表")
    public TyResponse queryAdmins(@RequestBody TyPageEntity queryAdminRequest) {
        IPage result = userService.queryAdmins(getPage(queryAdminRequest));
        if(null != result){
            return new TySuccessResponse(result);
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_GET_ERROR);
    }

    @Override
    @OperControllerLog(key = OperLogKeyConsts.GMS_GET_ADMIN_KEY, module = "用户管理", type = "查询管理员信息")
    public TyResponse getAdmin(@PathVariable("id") Long id){
        Map admin = userService.getAdmin(id);
        if(null != admin){
            return new TySuccessResponse(admin);
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_GET_ERROR);
    }

    @Override
    @OperControllerLog(key = OperLogKeyConsts.GMS_CREATE_ADMIN_KEY, module = "用户管理", type = "创建管理员")
    public TyResponse createAdmin(@RequestBody CreateAdminRequest createAdminRequest) {
        if(userService.createUser(UserTypeConsts.ADMIN, createAdminRequest)){
            return new TySuccessResponse("admin allocate successfully.");
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_REGISTRY_ERROR);
    }

    @Override
    @OperControllerLog(key = OperLogKeyConsts.GMS_UPDATE_ADMIN_KEY, module = "用户管理", type = "更新管理员")
    public TyResponse updateAdmin(@RequestBody UpdateAdminRequest updateAdminRequest){
        if(userService.updateUser(updateAdminRequest)){
            return new TySuccessResponse("admin update successfully.");
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_UPDATE_ERROR);
    }

    @Override
    @OperControllerLog(key = OperLogKeyConsts.GMS_QUERY_PROXYS_KEY, module = "用户管理", type = "查询代理会员列表")
    public TyResponse queryProxys(@RequestBody TyPageEntity queryProxyRequest) {
        IPage result = userService.queryProxys(getPage(queryProxyRequest));
        if(null != result){
            return new TySuccessResponse(result);
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_GET_ERROR);
    }

    @Override
    @OperControllerLog(key = OperLogKeyConsts.GMS_GET_PROXY_KEY, module = "用户管理", type = "查询代理会员信息")
    public TyResponse getProxy(@PathVariable("id") Long id){
        Map result = userService.getProxy(id);
        if(null != result){
            return new TySuccessResponse(result);
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_GET_ERROR);
    }

    @Override
    @OperControllerLog(key = OperLogKeyConsts.GMS_CREATE_PROXY_KEY, module = "用户管理", type = "分配代理会员")
    public TyResponse allocateProxy(@RequestBody AllocateProxyRequest allocateProxyRequest) {
        if(userService.createUser(UserTypeConsts.PROXY, allocateProxyRequest)){
            return new TySuccessResponse("proxy allocate successfully.");
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_REGISTRY_ERROR);
    }

    @Override
    @OperControllerLog(key = OperLogKeyConsts.GMS_UPDATE_PROXY_KEY, module = "用户管理", type = "更新代理会员")
    public TyResponse updateProxy(@RequestBody UpdateProxyRequest updateProxyRequest) {
        if(userService.updateUser(updateProxyRequest)){
            return new TySuccessResponse("proxy update successfully.");
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_UPDATE_ERROR);
    }

    @Override
    @OperControllerLog(key = OperLogKeyConsts.GMS_DELETE_USERS_KEY, module = "用户管理", type = "删除用户")
    public TyResponse deleteUser(@PathVariable("id") Long id) {
        if(userService.deleteUser(id)){
            return new TySuccessResponse("user delete successfully.");
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_REMOVE_ERROR);
    }

    @Override
    @OperControllerLog(key = OperLogKeyConsts.GMS_QUERY_USERS_KEY, module = "用户管理", type = "查询普通会员列表")
    public TyResponse queryUsers(@RequestBody QueryAccountRequest queryAccountRequest) {
        IPage result = userService.queryUsers(queryAccountRequest, getPage(queryAccountRequest));
        if(null != result){
            return new TySuccessResponse(result);
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_GET_ERROR);
    }


    @Override
    @OperControllerLog(key = OperLogKeyConsts.GMS_GET_USER_KEY, module = "用户管理", type = "查询普通会员信息")
    public TyResponse getUser(@PathVariable("id") Long id){
        Map result = userService.getUser(id);
        if(null != result){
            return new TySuccessResponse(result);
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_GET_ERROR);
    }

    @Override
    @OperControllerLog(key = OperLogKeyConsts.GMS_CREATE_USER_KEY, module = "用户管理", type = "注册普通会员")
    public TyResponse register(@RequestBody RegisterAccountRequest registerAccountRequest) {
        if(userService.createUser(UserTypeConsts.NORMALUSER, registerAccountRequest)){
            return new TySuccessResponse("user register successfully.");
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_REGISTRY_ERROR);
    }

    @Override
    @OperControllerLog(key = OperLogKeyConsts.GMS_UPDATE_USER_KEY, module = "用户管理", type = "更新普通会员")
    public TyResponse update(@RequestBody UpdateAccountRequest updateAccountRequest) {
        if(userService.updateUser(updateAccountRequest)){
            return new TySuccessResponse("user update successfully.");
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_UPDATE_ERROR);
    }

    @Override
    public void batchCreateUser(Integer sysUserCount) {

    }

    @Override
    public TyResponse getUserLoginHistory(TyPageEntity queryRequest) {
        IPage result = userService.getUserLoginHistory(getPage(queryRequest));
        if(null != result){
            return new TySuccessResponse(result);
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_GET_ERROR);
    }

    @Override
    public TyResponse getProxyLoginHistory(TyPageEntity queryRequest) {
        IPage result = userService.getProxyLoginHistory(getPage(queryRequest));
        if(null != result){
            return new TySuccessResponse(result);
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_GET_ERROR);
    }

    @Override
    public TyResponse getAdminLoginHistory(TyPageEntity queryRequest) {
        IPage result = userService.getAdminLoginHistory(getPage(queryRequest));
        if(null != result){
            return new TySuccessResponse(result);
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_GET_ERROR);
    }
}
