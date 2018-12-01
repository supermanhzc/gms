package com.taoyuan.gms.core.proxymanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taoyuan.framework.bs.mapper.TyBaseMapper;
import com.taoyuan.gms.model.entity.proxy.CardPwdWithdrawEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CardPwdCallbackMapper extends TyBaseMapper<CardPwdWithdrawEntity> {
}
