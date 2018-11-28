package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.admin.WebManageApi;
import com.taoyuan.gms.core.adminmanage.service.IGameSettingService;
import com.taoyuan.gms.core.adminmanage.service.IWebSettingService;
import com.taoyuan.gms.model.entity.admin.content.AnnouncementEntity;
import com.taoyuan.gms.model.entity.admin.web.GameSettingEntity;
import com.taoyuan.gms.model.entity.admin.web.WebSettingEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public WebSettingEntity getWebSetting() {
        return webSettingService.getOne(null);
    }

    @Override
    public WebSettingEntity updateWebSetting(WebSettingEntity webSetting) {
        WebSettingEntity dbValue = (WebSettingEntity)webSettingService.getOne(null);
        if(null == dbValue) {
            dbValue = new WebSettingEntity();
            dbValue.update(webSetting);
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
    public List<GameSettingEntity> getGameSetting() {
        return gameSettingService.list(null);
    }

    @Override
    public TyResponse updateGameSetting(List<GameSettingEntity> list) {
        if(CollectionUtils.isEmpty(list)){
            return new TySuccessResponse(list);
        }

        for(GameSettingEntity entity:list){
            if(StringUtils.isEmpty(entity.getGameName())){
                throw new ValidateException("游戏名称不能为空。");
            }
        }
        List<GameSettingEntity> dbValueList = new ArrayList<GameSettingEntity>();
        for(GameSettingEntity entity:list){
            GameSettingEntity dbValue = gameSettingService.getByName(entity.getGameName());
            dbValue.update(entity);
            dbValueList.add(dbValue);
        }
        gameSettingService.saveOrUpdateBatch(dbValueList);
        return new TySuccessResponse(dbValueList);
    }
}
