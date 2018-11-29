package com.taoyuan.gms.core.adminmanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taoyuan.gms.model.entity.admin.account.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
