package com.taoyuan.gms.core.adminmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.WebSettingMapper;
import com.taoyuan.gms.core.adminmanage.service.IWebSettingService;
import com.taoyuan.gms.model.entity.admin.web.WebSettingEntity;
import org.springframework.stereotype.Service;

@Service
public class WebSettingServiceImpl extends ServiceImpl<WebSettingMapper, WebSettingEntity> implements IWebSettingService {
}
