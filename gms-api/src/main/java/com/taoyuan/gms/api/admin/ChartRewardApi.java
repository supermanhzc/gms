package com.taoyuan.gms.api.admin;

import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.dto.admin.ChartsRequest;
import com.taoyuan.gms.model.entity.admin.web.ChartRankingEntity;
import com.taoyuan.gms.model.entity.admin.web.ChartRankingSettingEntity;
import com.taoyuan.gms.model.entity.admin.web.VChartRankingEntity;
import com.taoyuan.gms.model.entity.admin.web.VChartRankingSettingEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Api(value = "排行奖设置服务")
@RequestMapping("/rewardmgnt")
public interface ChartRewardApi {
    /**
     * 查询排行将显示名次
     * @return
     */
    @RequestMapping(value = "/ranking/retrieve", method = RequestMethod.POST)
    public TyResponse retrieveRanking();

    /**
     * 更改排行将显示名次
     * @param entity
     * @return
     */
    @RequestMapping(value = "/ranking/update", method = RequestMethod.PUT)
    public TyResponse updateRanking(@RequestBody ChartRankingSettingEntity entity);

    /**
     * 查询奖励
     * @param request
     * @return
     */
    @RequestMapping(value = "/charts/retrieve", method = RequestMethod.POST)
    public TyResponse retrieveCharts(@RequestBody ChartsRequest request);

    /**
     * 更改奖励
     * @param entityList
     * @return
     */
    @RequestMapping(value = "/charts/update", method = RequestMethod.PUT)
    public List<ChartRankingEntity> updateCharts(@RequestBody List<ChartRankingEntity> entityList);

    /**
     * 查询虚拟排行将显示名次
     * @return
     */
    @RequestMapping(value = "/vranking/retrieve", method = RequestMethod.POST)
    public TyResponse retrieveVRanking();

    /**
     * 更改虚拟排行将显示名次
     * @param entity
     * @return
     */
    @RequestMapping(value = "/vranking/update", method = RequestMethod.PUT)
    public TyResponse updateVRanking(@RequestBody VChartRankingSettingEntity entity);

    /**
     * 查询虚拟奖励
     * @param request
     * @return
     */
    @RequestMapping(value = "/vcharts/retrieve", method = RequestMethod.POST)
    public TyResponse retrieveVCharts(@RequestBody ChartsRequest request);

    /**
     * 更改虚拟奖励
     * @param entityList
     * @return
     */
    @RequestMapping(value = "/vcharts/update", method = RequestMethod.PUT)
    public TyResponse updateVCharts(@RequestBody List<VChartRankingEntity> entityList);

}
