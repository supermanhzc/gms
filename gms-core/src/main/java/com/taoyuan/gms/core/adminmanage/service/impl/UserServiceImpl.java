package com.taoyuan.gms.core.adminmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.framework.aaa.service.TyUserService;
import com.taoyuan.framework.common.constant.ResultCode;
import com.taoyuan.framework.common.constant.UserConsts;
import com.taoyuan.framework.common.entity.TyUser;
import com.taoyuan.framework.common.exception.TyExceptionUtil;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.common.consts.UserTypeConsts;
import com.taoyuan.gms.core.adminmanage.dao.UserMapper;
import com.taoyuan.gms.core.adminmanage.service.IUserService;
import com.taoyuan.gms.model.dto.admin.account.*;
import com.taoyuan.gms.model.entity.admin.account.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {
    @Autowired
    private TyUserService tyUserService;

    @Override
    public TyResponse createUser(Integer type, Object request){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(request, userEntity);

        TyUser tyUser = new TyUser();
        BeanUtils.copyProperties(request, tyUser);
        tyUser.setType(type);

        if (null == tyUser.getUserName()){
            tyUser.setUserName(tyUser.getPhone());
        }

        TyResponse response = tyUserService.register(tyUser);
        if(UserTypeConsts.ADMIN == tyUser.getType()){
            return response;
        }
        if(ResultCode.SUCCESS.getCode().toString().equals(response.getCode())){
            userEntity.setId(((TyUser)response.getData()).getId());
            if(this.save(userEntity)){
                return response;
            }
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_REGISTRY_ERROR);
    }

    @Override
    public TyResponse updateUser(Object request) {
        TyUser tyUser = new TyUser();
        BeanUtils.copyProperties(request, tyUser);

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(request, userEntity);
        TyResponse response = tyUserService.modify(tyUser);
        if(ResultCode.SUCCESS.getCode().toString().equals(response.getCode())){
            if(this.updateById(userEntity)){
                return response;
            }
        }
        throw TyExceptionUtil.buildException(ResultCode.USER_UPDATE_ERROR);
    }

    @Override
    public TyResponse deleteUser(Long id) {
        return tyUserService.delete(id);
    }

    @Override
    public IPage<Map<String, Object>> getAllUsers(List<Integer> types, IPage page) {
        QueryWrapper<TyUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().ne(TyUser::getStatus, UserConsts.DELETED);
        if(!CollectionUtils.isEmpty(types)){
            wrapper.lambda().in(TyUser::getType, types);
        }
        IPage<Map<String, Object>> tyUsers = tyUserService.pageMaps(page, wrapper);
        return tyUsers;
    }


}
