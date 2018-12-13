package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.bs.aspect.OperControllerLog;
import com.taoyuan.framework.common.entity.TyPageEntity;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.admin.LevelSettingApi;
import com.taoyuan.gms.common.util.CollectionsUtil;
import com.taoyuan.gms.core.adminmanage.dao.LevelSettingMapper;
import com.taoyuan.gms.core.adminmanage.service.ILevelSettingService;
import com.taoyuan.gms.model.entity.admin.web.LevelSettingEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class LevelSettingController extends BaseGmsController implements LevelSettingApi {

    @Autowired
    private ILevelSettingService levelSettingService;

    @Autowired
    private LevelSettingMapper levelSettingMapper;

    @Override
    @OperControllerLog(module = "等级管理", type = "查询等级配置")
    public TyResponse retrieve(@RequestBody TyPageEntity pageEntity) {
        Page page = getPage(pageEntity);
        return new TySuccessResponse(levelSettingMapper.selectPage(page, null));
    }

    @Override
    @OperControllerLog(module = "等级管理", type = "修改等级配置")
    public TyResponse update(@RequestBody List<LevelSettingEntity> levelSettingEntityList) {
        log.info("input is {}", levelSettingEntityList);
        validate(levelSettingEntityList);

        List<LevelSettingEntity> dbEntityList = new ArrayList<LevelSettingEntity>();
        for(LevelSettingEntity entity:levelSettingEntityList){
            LevelSettingEntity dbValue = getOne(entity.getName());
            dbValue.update(entity);
            dbEntityList.add(dbValue);
        }
        levelSettingService.saveOrUpdateBatch(dbEntityList);
        return new TySuccessResponse(dbEntityList);
    }

    private void validate(@RequestBody List<LevelSettingEntity> levelSettingEntityList) {
        if (CollectionsUtil.isNotEmpty(levelSettingEntityList)) {
            for (LevelSettingEntity entity : levelSettingEntityList) {
                if (null == entity.getName()) {
                    throw new ValidateException("等级不能为空。");
                }
                LevelSettingEntity dbValue = getOne(entity.getName());
                dbValue.update(entity);
            }
        }
    }

    private LevelSettingEntity getOne(String name) {
        return (LevelSettingEntity) levelSettingService.getObj(new QueryWrapper<LevelSettingEntity>().eq(
                "name", name));
    }
}
