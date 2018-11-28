package com.taoyuan.gms.core.proxymanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.proxymanage.dao.FundExchangeMapper;
import com.taoyuan.gms.core.proxymanage.service.IFundExchangeService;
import com.taoyuan.gms.model.entity.proxy.CardPwdWithdrawEntity;
import com.taoyuan.gms.model.entity.proxy.FundExchangeEntity;
import org.springframework.stereotype.Service;

@Service
public class FundExchangeServiceImpl extends ServiceImpl<FundExchangeMapper, FundExchangeEntity> implements IFundExchangeService {

    @Override
    public IPage getLatest10() {
        Page page = new Page(1, 10);
        QueryWrapper<FundExchangeEntity> wrapper = new QueryWrapper<FundExchangeEntity>();
        wrapper.orderByDesc("time");

        return page(page, wrapper);
    }
}
