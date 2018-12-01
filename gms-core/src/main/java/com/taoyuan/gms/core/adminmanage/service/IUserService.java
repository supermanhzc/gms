package com.taoyuan.gms.core.adminmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.dto.admin.account.*;
import com.taoyuan.gms.model.entity.admin.account.UserEntity;

import java.util.List;
import java.util.Map;

public interface IUserService extends IService<UserEntity> {
    TyResponse createUser(Integer type, Object request);
    TyResponse updateUser(Object request);


    TyResponse deleteUser(Long id);

    IPage<Map<String, Object>> getAllUsers(List<Integer> types, IPage page);
}
