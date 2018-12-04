package com.taoyuan.gms.core.proxymanage.controller;

import com.taoyuan.framework.aaa.controller.TyAuthController;
import com.taoyuan.framework.common.entity.TyUser;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.util.TyIpUtil;
import com.taoyuan.gms.api.proxy.ProxyUserApi;
import com.taoyuan.gms.core.adminmanage.service.IUserLoginService;
import com.taoyuan.gms.model.entity.admin.UserLoginEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class ProxyUserController extends BaseGmsProxyController implements ProxyUserApi {
    @Autowired
    private TyAuthController authController;

    @Autowired
    private IUserLoginService userLoginService;

    @Override
    public TyResponse login(TyUser userInfo) {
        authController.login(userInfo);

        UserLoginEntity userLogin = new UserLoginEntity();
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        userLogin.setMemberId(getCurrentUserId());
        userLogin.setMemberNickName(getCurrentUserName());
        String ip = TyIpUtil.getIpAddr(request);
        userLogin.setIp(ip);
        userLogin.setAddr(TyIpUtil.getAddressByIp(ip));
        userLogin.setType(2);
        userLogin.setStatus(1);
        userLoginService.saveOrUpdate(userLogin);
        return new TyResponse();
    }
}
