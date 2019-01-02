package com.taoyuan.gms.api.proxy;

import com.taoyuan.framework.common.entity.TyUser;
import com.taoyuan.framework.common.http.TyResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 代理操作，登录等
 */
@Api(value = "代理登录操作")
@RequestMapping("/login/proxy")
public interface ProxyLoginApi {
    @RequestMapping(value = "", method = RequestMethod.POST)
    TyResponse login(@RequestBody TyUser userInfo);
}
