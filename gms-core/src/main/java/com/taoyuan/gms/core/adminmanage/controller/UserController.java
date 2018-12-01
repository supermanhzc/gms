package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.framework.aaa.service.TyUserService;
import com.taoyuan.framework.common.http.TyResponse;
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
public class UserController extends BaseController implements UserApi {

    @Autowired
    private IUserService userService;

    @Autowired
    private TyUserService tyUserService;

//    @Override
//    public IPage<Map<String, Object>> getAllProxys(Integer pageIndex, Integer pageSize) {
//        List<Integer> types = new ArrayList<Integer>();
//        types.add(UserTypeConsts.PROXY);
//        return userService.getAllUsers(types, getPage(pageIndex, pageSize));
//    }
//
//    @Override
//    public IPage<Map<String, Object>> getAllRegisterUsers(Integer pageIndex, Integer pageSize) {
//        List<Integer> types = new ArrayList<Integer>();
//        types.add(UserTypeConsts.PROXY);
//        return userService.getAllUsers(types, getPage(pageIndex, pageSize));
//    }

    @Override
    public IPage<Map<String, Object>> getAllUsers(Integer pageIndex, Integer pageSize) {
        return userService.getAllUsers(null, getPage(pageIndex, pageSize));
    }

    @Override
    public TyResponse deleteUser(Long id) {
        return userService.deleteUser(id);
    }

    @Override
    public TyResponse register(RegisterAccountRequest registerAccountRequest) {
        return userService.createUser(UserTypeConsts.NORMALUSER, registerAccountRequest);
    }

    @Override
    public TyResponse update(UpdateAccountRequest updateAccountRequest) {
        return userService.updateUser(updateAccountRequest);
    }

    @Override
    public TyResponse allocateProxy(AllocateProxyRequest allocateProxyRequest) {
        return userService.createUser(UserTypeConsts.PROXY, allocateProxyRequest);
    }

    @Override
    public TyResponse updateProxy(UpdateProxyRequest updateProxyRequest) {
        return userService.updateUser(updateProxyRequest);
    }

    @Override
    public TyResponse createAdmin(CreateAdminRequest createAdminRequest) {
        return userService.createUser(UserTypeConsts.ADMIN, createAdminRequest);
    }

    @Override
    public TyResponse updateAdmin(UpdateAdminRequest updateAdminRequest){
        return userService.updateUser(updateAdminRequest);
    }

    @Override
    public void batchCreateUser(Integer sysUserCount) {

    }
}
