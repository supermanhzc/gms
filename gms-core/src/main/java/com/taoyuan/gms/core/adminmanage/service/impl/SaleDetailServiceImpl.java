package com.taoyuan.gms.core.adminmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.SaleDetailMapper;
import com.taoyuan.gms.core.adminmanage.service.ISaleDetailService;
import com.taoyuan.gms.model.entity.admin.SaleDetailEntity;
import org.springframework.stereotype.Service;

@Service
public class SaleDetailServiceImpl extends ServiceImpl<SaleDetailMapper,SaleDetailEntity> implements ISaleDetailService {
}
