package com.taoyuan.gms.core.sitemanage.account.service;

import com.taoyuan.framework.bs.service.TyBaseService;
import com.taoyuan.gms.model.entity.site.account.GoldEntity;

import java.math.BigDecimal;

public interface IGoldService extends TyBaseService<GoldEntity> {

    void addGold(Long memberId, BigDecimal goldNum);

    void reduceGold(Long memberId, BigDecimal goldNum);

    GoldEntity getGlodByMemberId(Long memberId);
}
