package com.taoyuan.gms.core.adminmanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.gms.model.dto.admin.account.QueryAccountRequest;
import com.taoyuan.gms.model.entity.admin.account.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    List<Map> queryRegisterUser(IPage mapPage, QueryAccountRequest queryAccountRequest);
    List<Map> queryProxy(IPage mapPage);
    List<Map> queryAdmin(IPage mapPage);
}