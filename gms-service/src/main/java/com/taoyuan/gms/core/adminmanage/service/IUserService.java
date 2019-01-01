package com.taoyuan.gms.core.adminmanage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taoyuan.framework.common.entity.TyUser;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.dto.admin.account.*;
import com.taoyuan.gms.model.entity.admin.account.UserEntity;

import java.util.List;
import java.util.Map;

public interface IUserService extends IService<UserEntity> {
    boolean createUser(Integer type, Object request);
    boolean updateUser(Object request);


    boolean deleteUser(Long id);

    IPage queryUsers(QueryAccountRequest queryAccountRequest, IPage page);
    IPage queryProxys(IPage page);
    IPage queryAdmins(IPage page);

    Map getUser(Long id);
    Map getProxy(Long id);
    Map getAdmin(Long id);

    List<TyUser> queryAllProxys();

    IPage getUserLoginHistory(IPage page);
    IPage getProxyLoginHistory(IPage page);
    IPage getAdminLoginHistory(IPage page);

}
