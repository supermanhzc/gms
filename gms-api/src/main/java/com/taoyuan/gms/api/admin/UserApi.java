package com.taoyuan.gms.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.framework.common.entity.TyPageEntity;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.dto.admin.account.*;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "会员管理")
@RequestMapping("/users")
public interface UserApi{
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    TyResponse queryUsers(@RequestBody QueryAccountRequest queryAccountRequest);

    @RequestMapping(value = "/getProxy", method = RequestMethod.POST)
    TyResponse queryProxys(TyPageEntity queryProxyRequest);

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    TyResponse deleteUser(@PathVariable("id") Long id);

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    TyResponse register(@RequestBody RegisterAccountRequest registerAccountRequest);

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    TyResponse update(@RequestBody UpdateAccountRequest updateAccountRequest);

    @RequestMapping(value = "/allocateProxy", method = RequestMethod.POST)
    TyResponse allocateProxy(@RequestBody AllocateProxyRequest allocateProxyRequest);

    @RequestMapping(value = "/updateProxy", method = RequestMethod.POST)
    TyResponse updateProxy(@RequestBody UpdateProxyRequest updateProxyRequest);

    @RequestMapping(value = "/createAdmin", method = RequestMethod.POST)
    TyResponse createAdmin(CreateAdminRequest createAdminRequest);

    @RequestMapping(value = "/updateAdmin", method = RequestMethod.POST)
    TyResponse updateAdmin(@RequestBody UpdateAdminRequest updateAdminRequest);

    @RequestMapping(value = "/batchsys", method = RequestMethod.POST)
    void batchCreateUser(@RequestParam("count") Integer sysUserCount);
}
