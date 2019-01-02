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
import com.taoyuan.framework.common.http.TySuccessResponse;
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
    public boolean createUser(Integer type, Object request){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(request, userEntity);

        TyUser tyUser = new TyUser();
        BeanUtils.copyProperties(request, tyUser);
        tyUser.setType(type);

        if (null == tyUser.getUserName()){
            tyUser.setUserName(tyUser.getPhone());
        }

        boolean isUserAdded = tyUserService.register(tyUser);
        if(UserTypeConsts.ADMIN == tyUser.getType()){
            return isUserAdded;
        }
        if(isUserAdded){
            userEntity.setId(tyUser.getId());
            return this.save(userEntity);
        }
        return false;
    }

    @Override
    public boolean updateUser(Object request) {
        TyUser tyUser = new TyUser();
        BeanUtils.copyProperties(request, tyUser);

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(request, userEntity);
        boolean isUserModified = tyUserService.modify(tyUser);

        if (isUserModified){
            if(userEntity.canUpdate()&& UserTypeConsts.ADMIN != tyUserService.getById(tyUser.getId()).getType()){
                return this.updateById(userEntity);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(Long id) {
        return tyUserService.delete(id);
    }

    @Override
    public IPage queryUsers(QueryAccountRequest queryAccountRequest, IPage page) {
        return page.setRecords(baseMapper.queryRegisterUser(page, queryAccountRequest));
    }

    @Override
    public IPage queryProxys(IPage page) {
        return page.setRecords(baseMapper.queryProxy(page));
    }

    @Override
    public IPage queryAdmins(IPage page) {
        return page.setRecords(baseMapper.queryAdmin(page));
    }

    @Override
    public Map getUser(Long id) {
        return baseMapper.getUser(id);
    }

    @Override
    public Map getProxy(Long id) {
        return baseMapper.getProxy(id);
    }

    @Override
    public Map getAdmin(Long id) {
        return baseMapper.getAdmin(id);
    }

    @Override
    public List<TyUser> queryAllProxys() {
        QueryWrapper<TyUser> wrapper = new QueryWrapper<TyUser>();
        wrapper.lambda().eq(TyUser::getType,2);
        return tyUserService.list(wrapper);
    }
  
    @Override
    public IPage getUserLoginHistory(IPage page) {
        return page.setRecords(baseMapper.getLoginHistory(page, UserTypeConsts.NORMALUSER));
    }

    @Override
    public IPage getProxyLoginHistory(IPage page) {
        return page.setRecords(baseMapper.getLoginHistory(page, UserTypeConsts.PROXY));
    }

    @Override
    public IPage getAdminLoginHistory(IPage page) {
        return page.setRecords(baseMapper.getLoginHistory(page, UserTypeConsts.ADMIN));
    }
}
