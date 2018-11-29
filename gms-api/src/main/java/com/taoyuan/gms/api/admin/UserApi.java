package com.taoyuan.gms.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.dto.admin.UserDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "会员管理")
@RequestMapping("/users")
public interface UserApi{
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    IPage<Map<String, Object>> getAllUsers(@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize);

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    TyResponse modifyUser(@RequestBody UserDto userDto);

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    TyResponse deleteUser(@PathVariable("id") Long id);

    @RequestMapping(value = "/create/normal", method = RequestMethod.POST)
    TyResponse createUser(@RequestBody UserDto userDto);

    @RequestMapping(value = "/create/sys", method = RequestMethod.POST)
    void batchCreateUser(@RequestParam("count") Integer sysUserCount);
}
