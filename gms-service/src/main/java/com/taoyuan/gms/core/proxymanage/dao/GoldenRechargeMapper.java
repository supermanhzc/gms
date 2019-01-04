package com.taoyuan.gms.core.proxymanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taoyuan.gms.model.entity.proxy.GoldenRechargeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

@Mapper
public interface GoldenRechargeMapper extends BaseMapper<GoldenRechargeEntity> {

    BigDecimal getRechargeGoldSumByUserId(Long id);
}
