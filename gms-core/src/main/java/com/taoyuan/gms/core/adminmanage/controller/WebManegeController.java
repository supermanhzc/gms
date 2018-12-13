package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.bs.aspect.OperControllerLog;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.admin.WebManageApi;
import com.taoyuan.gms.core.adminmanage.service.IGameSettingService;
import com.taoyuan.gms.core.adminmanage.service.IWebSettingService;
import com.taoyuan.gms.model.entity.admin.web.GameSettingEntity;
import com.taoyuan.gms.model.entity.admin.web.WebSettingEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class WebManegeController implements WebManageApi {
    @Autowired
    IWebSettingService webSettingService;

    @Autowired
    IGameSettingService gameSettingService;

    @Override
    @OperControllerLog(module = "网站管理", type = "查询网站配置")
    public TyResponse getWebSetting() {
        return new TySuccessResponse(webSettingService.getOne(null));
    }

    @Override
    @OperControllerLog(module = "网站管理", type = "修改网站配置")
    public TyResponse updateWebSetting(@RequestBody WebSettingEntity webSetting) {
        WebSettingEntity dbValue = (WebSettingEntity) webSettingService.getOne(null);
        if (null == dbValue) {
            dbValue = new WebSettingEntity();
            dbValue.update(webSetting);
            webSetting.setCreateTime(new Date());
            webSetting.setCreateUser(TySession.getCurrentUser().getUserId());
        } else {
            dbValue.update(webSetting);
            webSetting.setUpdateUser(TySession.getCurrentUser().getUserId());
            webSetting.setCreateTime(new Date());
        }
        webSettingService.saveOrUpdate(webSetting);
        return new TySuccessResponse(dbValue);
    }

    @Override
    @OperControllerLog(module = "游戏管理", type = "查询游戏配置")
    public TyResponse getGameSetting() {
        return new TySuccessResponse(gameSettingService.list(new QueryWrapper<GameSettingEntity>()));
    }

    @Override
    @OperControllerLog(module = "游戏管理", type = "修改游戏配置")
    public TyResponse updateGameSetting(@RequestBody List<GameSettingEntity> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new TySuccessResponse(list);
        }

        for (GameSettingEntity entity : list) {
            if (StringUtils.isEmpty(entity.getGameName())) {
                throw new ValidateException("游戏名称不能为空。");
            }
        }
        List<GameSettingEntity> dbValueList = new ArrayList<GameSettingEntity>();
        for (GameSettingEntity entity : list) {
            GameSettingEntity dbValue = gameSettingService.getByName(entity.getGameName());
            dbValue.update(entity);
            dbValueList.add(dbValue);
        }
        gameSettingService.saveOrUpdateBatch(dbValueList);
        return new TySuccessResponse(dbValueList);
    }
}
