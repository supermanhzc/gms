package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.admin.WebManageApi;
import com.taoyuan.gms.core.adminmanage.service.IGameSettingService;
import com.taoyuan.gms.core.adminmanage.service.IWebSettingService;
import com.taoyuan.gms.model.entity.admin.content.AnnouncementEntity;
import com.taoyuan.gms.model.entity.admin.web.GameSettingEntity;
import com.taoyuan.gms.model.entity.admin.web.WebSettingEntity;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class WebManegeController implements WebManageApi {
    @Autowired
    IWebSettingService webSettingService;

    @Autowired
    IGameSettingService gameSettingService;

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

    @Override
    public Map<String, Object> getGameSetting() {
        return gameSettingService.getMap(new QueryWrapper<GameSettingEntity>());
    }

    @Override
    public TyResponse updateGameSetting(List<GameSettingEntity> list) {
        if(CollectionUtils.isEmpty(list)){
            return new TySuccessResponse(list);
        }

        gameSettingService.saveOrUpdateBatch(list);
        return new TySuccessResponse(list);
    }
}
