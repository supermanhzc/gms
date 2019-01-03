package com.taoyuan.gms.core.sitemanage.account.controller;

import com.taoyuan.framework.bs.aspect.OperControllerLog;
import com.taoyuan.framework.common.entity.TyUser;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.api.site.MemberLoginApi;
import com.taoyuan.gms.core.adminmanage.dao.UserMapper;
import com.taoyuan.gms.core.proxymanage.controller.AbstractLoginController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class MemberLoginController extends AbstractLoginController implements MemberLoginApi {

    @Autowired
    private UserMapper userMapper;

    @Override
    @OperControllerLog(module = "会员管理", type = "会员登录")
    public TyResponse login(@RequestBody TyUser userInfo) {
        return super.login(userInfo);
    }

    @Override
    public Map getUserInfo(String username) {
        return userMapper.getUserByUserName(username);
    }

    @Override
    public int getUserType() {
        return 1;
    }
}
