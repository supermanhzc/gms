package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.admin.ChartRewardApi;
import com.taoyuan.gms.core.adminmanage.dao.ChartRankingMapper;
import com.taoyuan.gms.core.adminmanage.dao.VChartRankingMapper;
import com.taoyuan.gms.core.adminmanage.service.ICHartRankingSettingService;
import com.taoyuan.gms.core.adminmanage.service.IChartRankingService;
import com.taoyuan.gms.core.adminmanage.service.IVChartRankingService;
import com.taoyuan.gms.core.adminmanage.service.IVChartRankingSettingService;
import com.taoyuan.gms.model.dto.admin.charts.ChartsRequest;
import com.taoyuan.gms.model.entity.admin.web.ChartRankingEntity;
import com.taoyuan.gms.model.entity.admin.web.ChartRankingSettingEntity;
import com.taoyuan.gms.model.entity.admin.web.VChartRankingEntity;
import com.taoyuan.gms.model.entity.admin.web.VChartRankingSettingEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class ChartRewardController extends BaseGmsController implements ChartRewardApi {
    @Autowired
    private ICHartRankingSettingService chartRankingSettingService;

    @Autowired
    private IChartRankingService chartRankingService;

    @Autowired
    private ChartRankingMapper chartRankingMapper;

    @Autowired
    private VChartRankingMapper vChartRankingMapper;

    @Autowired
    private IVChartRankingSettingService vChartRankingSettingService;

    @Autowired
    private IVChartRankingService vChartRankingService;

    @Override
    public TyResponse retrieveRanking() {
        ChartRankingSettingEntity entity = chartRankingSettingService.getOne(null);
        if (null == entity) {
            entity = new ChartRankingSettingEntity();
            entity.setRankingCount(20);
            chartRankingSettingService.save(entity);
        }
        return new TySuccessResponse(entity);
    }

    @Override
    public TyResponse updateRanking(@RequestBody ChartRankingSettingEntity entity) {
        ChartRankingSettingEntity dbValue = chartRankingSettingService.getOne(null);
        dbValue.setRankingCount(entity.getRankingCount());
        chartRankingSettingService.saveOrUpdate(dbValue);
        return new TySuccessResponse(dbValue);
    }

    @Override
    public TyResponse retrieveCharts(@RequestBody ChartsRequest request) {
        int count = 0;
        if (request.getCount() != 0) {
            count = request.getCount();
        } else {
            ChartRankingSettingEntity dbValue = chartRankingSettingService.getOne(null);
            count = dbValue.getRankingCount();
        }

        QueryWrapper<ChartRankingEntity> wrapper = new QueryWrapper<ChartRankingEntity>();
        wrapper.orderByAsc("ranking");

        Page page = new Page(1, count);
        IPage<ChartRankingEntity> result = chartRankingMapper.selectMapsPage(page, wrapper);
        ;
        return new TySuccessResponse(result);
    }

    @Override
    public List<ChartRankingEntity> updateCharts(@RequestBody List<ChartRankingEntity> entityList) {
        log.info("input is {}", entityList);
        List<ChartRankingEntity> dbEntityList = new ArrayList<ChartRankingEntity>();
        for (ChartRankingEntity entity : entityList) {
            if (0 == entity.getRanking()) {
                throw new ValidateException("排行id不能为空。");
            }

            QueryWrapper<ChartRankingEntity> wrapper = new QueryWrapper<ChartRankingEntity>();
            wrapper.lambda().eq(ChartRankingEntity::getRanking, entity.getRanking());
            ChartRankingEntity dbValue = chartRankingService.getOne(wrapper);
            if (null == dbValue) {
                dbValue = entity;
            } else {
                if (entity.getChart() != 0) {
                    dbValue.setChart(entity.getChart());
                }
            }
            dbEntityList.add(dbValue);
        }
        chartRankingService.saveOrUpdateBatch(dbEntityList);
        return dbEntityList;
    }

    @Override
    public TyResponse retrieveVRanking() {
        VChartRankingSettingEntity entity = vChartRankingSettingService.getOne(null);
        if (null == entity) {
            entity = new VChartRankingSettingEntity();
            entity.setRankingCount(20);
            entity.setBaseChart(200000);
            vChartRankingSettingService.save(entity);
        }
        return new TySuccessResponse(entity);
    }

    @Override
    public TyResponse updateVRanking(@RequestBody VChartRankingSettingEntity entity) {
        VChartRankingSettingEntity dbValue = vChartRankingSettingService.getOne(null);
        if (null == dbValue) {
            throw new ValidateException("对象不存在。");
        }
        if (entity.getRankingCount() != 0) {
            dbValue.setRankingCount(entity.getRankingCount());
        }
        if (entity.getBaseChart() != 0) {
            dbValue.setBaseChart(entity.getBaseChart());
        }
        vChartRankingSettingService.saveOrUpdate(dbValue);
        return new TySuccessResponse(dbValue);
    }

    @Override
    public TyResponse retrieveVCharts(@RequestBody ChartsRequest request) {
        int count = 0;
        if (request.getCount() != 0) {
            count = request.getCount();
        } else {
            VChartRankingSettingEntity dbValue = vChartRankingSettingService.getOne(null);
            if (null == dbValue) {
                //默认是10
                count = 10;
            } else {
                count = vChartRankingSettingService.getOne(null).getRankingCount();
            }
        }

        QueryWrapper<VChartRankingEntity> wrapper = new QueryWrapper<VChartRankingEntity>();
        wrapper.orderByAsc("ranking");

        Page page = new Page(1, count);
        IPage result = vChartRankingMapper.selectMapsPage(page, wrapper);

        return new TySuccessResponse(result);
    }

    @Override
    public TyResponse updateVCharts(@RequestBody List<VChartRankingEntity> entityList) {
        log.info("input is {}", entityList);
        List<VChartRankingEntity> dbEntityList = new ArrayList<VChartRankingEntity>();
        for (VChartRankingEntity entity : entityList) {
            if (0 == entity.getRanking()) {
                throw new ValidateException("排行id不能为空。");
            }

            QueryWrapper<VChartRankingEntity> wrapper = new QueryWrapper<VChartRankingEntity>();
            wrapper.lambda().eq(VChartRankingEntity::getRanking, entity.getRanking());
            VChartRankingEntity dbValue = vChartRankingService.getOne(wrapper);
            if (null == dbValue) {
                dbValue = entity;
            } else {
                if (entity.getChart() != 0) {
                    dbValue.setChart(entity.getChart());
                }
            }
            dbEntityList.add(dbValue);
        }
        vChartRankingService.saveOrUpdateBatch(dbEntityList);
        return new TySuccessResponse(dbEntityList);
    }
}
