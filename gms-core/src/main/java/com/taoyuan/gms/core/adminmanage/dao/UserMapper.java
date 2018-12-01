package com.taoyuan.gms.core.adminmanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.gms.model.dto.admin.account.QueryAccountRequest;
import com.taoyuan.gms.model.entity.admin.account.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    List queryRegisterUser(IPage mapPage, QueryAccountRequest queryAccountRequest);
    List queryProxy(IPage mapPage);
}
