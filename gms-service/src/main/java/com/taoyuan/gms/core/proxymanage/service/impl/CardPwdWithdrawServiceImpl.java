package com.taoyuan.gms.core.proxymanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.framework.bs.service.TyBaseServiceImpl;
import com.taoyuan.gms.core.proxymanage.dao.CardPwdCallbackMapper;
import com.taoyuan.gms.core.proxymanage.service.ICardPwdWithdrawService;
import com.taoyuan.gms.model.entity.proxy.CardPwdWithdrawEntity;
import org.springframework.stereotype.Service;

@Service
public class CardPwdWithdrawServiceImpl extends TyBaseServiceImpl<CardPwdCallbackMapper, CardPwdWithdrawEntity> implements
        ICardPwdWithdrawService {
    @Override
    public IPage getLatest10() {
        Page page = new Page(1, 10);
        QueryWrapper<CardPwdWithdrawEntity> wrapper = new QueryWrapper<CardPwdWithdrawEntity>();
        wrapper.orderByDesc("time");

        return page(page, wrapper);
    }
}
