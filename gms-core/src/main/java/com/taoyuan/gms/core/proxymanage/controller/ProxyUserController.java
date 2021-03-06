package com.taoyuan.gms.core.proxymanage.controller;

import com.taoyuan.framework.bs.aspect.OperControllerLog;
import com.taoyuan.framework.common.entity.TyUser;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.api.proxy.ProxyLoginApi;
import com.taoyuan.gms.core.adminmanage.dao.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class ProxyUserController extends AbstractLoginController implements ProxyLoginApi {

    @Autowired
    private UserMapper userMapper;

    @Override
    @OperControllerLog(module = "代理登录管理", type = "代理登录")
    public TyResponse login(@RequestBody TyUser userInfo) {
        return super.login(userInfo);
    }

    @Override
    public Map getUserInfo(String username) {
        return userMapper.getProxyByUserName(username);
    }

    @Override
    public int getUserType() {
        return 2;
    }

}
