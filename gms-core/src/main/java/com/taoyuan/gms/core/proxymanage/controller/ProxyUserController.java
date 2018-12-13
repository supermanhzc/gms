package com.taoyuan.gms.core.proxymanage.controller;

import com.taoyuan.framework.aaa.controller.TyAuthController;
import com.taoyuan.framework.bs.aspect.OperControllerLog;
import com.taoyuan.framework.common.entity.TyUser;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.framework.common.util.TyIpUtil;
import com.taoyuan.gms.api.proxy.ProxyUserApi;
import com.taoyuan.gms.core.adminmanage.service.IUserLoginService;
import com.taoyuan.gms.core.proxymanage.service.IProxyOperService;
import com.taoyuan.gms.model.entity.admin.UserLoginEntity;
import com.taoyuan.gms.model.entity.proxy.ProxyOperEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class ProxyUserController extends BaseGmsProxyController implements ProxyUserApi {
    @Autowired
    private TyAuthController authController;

    @Autowired
    private IUserLoginService userLoginService;

    @Autowired
    private IProxyOperService proxyOperService;

    @Override
    @OperControllerLog(module = "代理管理", type = "代理登录")
    public TyResponse login(@RequestBody TyUser userInfo) {
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

        saveOperation(2, ip,getCurrentUserBalance());
        return new TyResponse();
    }

    //只记录代理的操作日志，其他不记录
    private void saveOperation(int type, String ip,BigDecimal account) {
        if (2 == type) {
            ProxyOperEntity oper = new ProxyOperEntity();
            //TODO真实数据，暂时写0
            oper.setAccount(account);
            oper.setDescription("登录");
            oper.setType(4);
            oper.setMoneyChanged(BigDecimal.ZERO);
            oper.setProxyId(TySession.getCurrentUser().getUserId());
            oper.setProxyName(TySession.getCurrentUser().getName());
            oper.setDescription(ip);
            proxyOperService.save(oper);
        }
    }

}
