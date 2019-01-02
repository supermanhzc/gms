package com.taoyuan.gms.core.adminmanage.controller;

import com.taoyuan.framework.bs.aspect.OperControllerLog;
import com.taoyuan.framework.common.entity.TyUser;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.api.admin.AdminLoginApi;
import com.taoyuan.gms.core.adminmanage.dao.UserMapper;
import com.taoyuan.gms.core.proxymanage.controller.AbstractLoginController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class AdminLoginController extends AbstractLoginController implements AdminLoginApi {

    @Autowired
    private UserMapper userMapper;

    @Override
    @OperControllerLog(module = "管理员登录管理", type = "管理员登录")
    public TyResponse login(@RequestBody TyUser userInfo) {
        return super.login(userInfo);
    }

    @Override
    public Map getUserInfo(String username) {
        return userMapper.getAdminByUserName(username);
    }

    @Override
    public int getUserType() {
        return 3;
    }
}
