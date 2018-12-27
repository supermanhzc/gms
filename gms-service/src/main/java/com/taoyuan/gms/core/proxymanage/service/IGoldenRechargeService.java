package com.taoyuan.gms.core.proxymanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taoyuan.gms.model.entity.proxy.GoldenRechargeEntity;

import java.math.BigDecimal;
import java.util.Date;

public interface IGoldenRechargeService extends IService<GoldenRechargeEntity> {

    GoldenRechargeEntity getById(Long id);

    /**
     * 查询某个代理在某个时间段的销售统计
     * @param id 代理id
     * @param start 开始时间
     * @param end 结束时间
     * @return
     */
    BigDecimal getStatistic(Long id, Date start, Date end);
}
