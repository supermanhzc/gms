package com.taoyuan.gms.api.admin;

import com.taoyuan.framework.common.entity.TyUser;
import com.taoyuan.framework.common.http.TyResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "管理员登录操作")
@RequestMapping("/login/admin")
public interface AdminLoginApi {
    @RequestMapping(value = "", method = RequestMethod.POST)
    TyResponse login(@RequestBody TyUser userInfo);
}
