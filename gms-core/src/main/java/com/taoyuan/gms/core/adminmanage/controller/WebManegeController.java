package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.gms.api.admin.WebManageApi;
import com.taoyuan.gms.core.adminmanage.service.IWebSettingService;
import com.taoyuan.gms.model.entity.admin.content.AnnouncementEntity;
import com.taoyuan.gms.model.entity.admin.web.WebSettingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
public class WebManegeController implements WebManageApi {
    @Autowired
    IWebSettingService webSettingService;

    @Override
    public Map<String, Object> getWebSetting() {
        return webSettingService.getMap(new QueryWrapper<WebSettingEntity>());
    }

    @Override
    public WebSettingEntity updateWebSetting(WebSettingEntity webSetting) {
        WebSettingEntity dbValue = (WebSettingEntity)webSettingService.getObj(new QueryWrapper<WebSettingEntity>().eq("id", webSetting.getId()));
        if(null == dbValue) {
            webSetting.setCreateTime(new Date());
            webSetting.setCreateUser(TySession.getCurrentUser().getUserId());
        }else{
            dbValue.update(webSetting);
            webSetting.setUpdateUser(TySession.getCurrentUser().getUserId());
            webSetting.setCreateTime(new Date());
        }
        webSettingService.saveOrUpdate(webSetting);
        return dbValue;
    }
}
