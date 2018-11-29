package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.aaa.controller.TyAuthController;
import com.taoyuan.framework.aaa.service.TyUserService;
import com.taoyuan.framework.common.constant.ErrorCode;
import com.taoyuan.framework.common.constant.ResultCode;
import com.taoyuan.framework.common.entity.TyUser;
import com.taoyuan.framework.common.exception.TyExceptionUtil;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.admin.UserApi;
import com.taoyuan.gms.core.adminmanage.service.IUserService;
import com.taoyuan.gms.model.dto.admin.UserDto;
import com.taoyuan.gms.model.entity.admin.account.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class UserController extends BaseController implements UserApi {

    @Autowired
    private IUserService userService;

    @Autowired
    private TyAuthController tyAuthController;

    @Override
    public IPage<Map<String, Object>> getAllUsers(Integer pageIndex, Integer pageSize) {
        Page page = getPage(pageIndex, pageSize);
        return userService.pageMaps(page, null);
    }

    @Override
    public TyResponse modifyUser(UserDto userDto) {
        TyUser tyUser = new TyUser();
        BeanUtils.copyProperties(userDto, tyUser);

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        TyResponse response = tyAuthController.modify(tyUser);
        if(ResultCode.SUCCESS.getCode().toString().equals(response.getCode())){
            if(userService.updateById(userEntity)){
                return response;
            }
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_UPDATE_ERROR);
    }

    @Override
    public TyResponse deleteUser(Long id) {
        return tyAuthController.delete(id);
    }

    @Override
    public TyResponse createUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);

        TyUser tyUser = new TyUser();
        BeanUtils.copyProperties(userDto, tyUser);
        TyResponse response = tyAuthController.register(tyUser);
        if(ResultCode.SUCCESS.getCode().toString().equals(response.getCode())){
            userEntity.setId(((TyUser)response.getData()).getId());
            if(userService.save(userEntity)){
                return response;
            }
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_REGISTRY_ERROR);
    }

    @Override
    public void batchCreateUser(Integer sysUserCount) {

    }
}
