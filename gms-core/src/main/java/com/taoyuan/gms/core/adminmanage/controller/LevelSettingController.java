package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.gms.api.admin.LevelSettingApi;
import com.taoyuan.gms.core.adminmanage.service.ILevelSettingService;
import com.taoyuan.gms.model.entity.admin.web.LevelSettingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class LevelSettingController extends BaseController implements LevelSettingApi {

    @Autowired
    private ILevelSettingService levelSettingService;

    @Override
    public IPage<Map<String, Object>> retrieve(Map<String, Object> map) {
        Page page = getPage(map);
        return levelSettingService.pageMaps(page, null);
    }

    @Override
    public List<LevelSettingEntity> update(List<LevelSettingEntity> levelSettingEntityList) {
        levelSettingService.saveOrUpdateBatch(levelSettingEntityList);
        return levelSettingEntityList;
    }
}
