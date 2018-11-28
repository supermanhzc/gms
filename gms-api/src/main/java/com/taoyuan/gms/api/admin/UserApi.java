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
    @RequestMapping(value = "/", method = RequestMethod.GET)
    IPage<Map<String, Object>> getAllUsers(@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize);

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    void modifyUser(@RequestBody UserDto userDto);

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void deleteUser(@PathVariable("id") Integer id);

    @RequestMapping(value = "/", method = RequestMethod.POST)
    void createUser(@RequestBody UserDto userDto);

    @RequestMapping(value = "/sys", method = RequestMethod.POST)
    void batchCreateUser(@RequestParam("count") Integer sysUserCount);
}
