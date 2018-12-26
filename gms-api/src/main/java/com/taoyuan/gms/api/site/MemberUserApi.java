package com.taoyuan.gms.api.site;

import com.taoyuan.framework.common.entity.TyUser;
import com.taoyuan.framework.common.http.TyResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "代理用户操作")
@RequestMapping("/login/member")
public interface MemberUserApi {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    TyResponse login(TyUser userInfo);
}
