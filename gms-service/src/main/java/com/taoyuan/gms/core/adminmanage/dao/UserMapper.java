package com.taoyuan.gms.core.adminmanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.gms.model.dto.admin.account.QueryAccountRequest;
import com.taoyuan.gms.model.entity.admin.account.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    List<Map> queryRegisterUser(IPage mapPage, QueryAccountRequest queryAccountRequest);
    List<Map> queryProxy(IPage mapPage);
    List<Map> queryAdmin(IPage mapPage);

    List<Map> selectUser();
    List<Map> selectProxy();
    List<Map> selectAdmin();

    Map getUser(Long id);
    Map getProxy(Long id);
    Map getAdmin(Long id);

    Map getUserByUserName(String username);
    Map getProxyByUserName(String username);
    Map getAdminByUserName(String username);

    List<Map> getLoginHistory(IPage mapPage, @Param("userType") Integer userType);
}
