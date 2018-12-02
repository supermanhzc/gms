package com.taoyuan.gms.api.admin;

import com.taoyuan.framework.common.entity.TyPageEntity;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.dto.admin.statistic.JuniorCommissionsPageRequest;
import com.taoyuan.gms.model.dto.admin.statistic.MemberLoginRequest;
import com.taoyuan.gms.model.dto.admin.charts.ChartsRewardPageRequest;
import com.taoyuan.gms.model.dto.admin.charts.VChartsRewardPageRequest;
import com.taoyuan.gms.model.dto.admin.chipin.ChipinWagePageRequest;
import com.taoyuan.gms.model.dto.admin.lossrebate.LossRebateRequest;
import com.taoyuan.gms.model.dto.admin.statistic.SaleStatisticsRequest;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "记录查询")
@RequestMapping("/records")
public interface RecordsQueryApi {
    /**
     * 查询所有验证码信息
     *
     * @return
     */
    @RequestMapping(value = "/verificationcode/page/index={pageIndex}&size={pageSize}", method = RequestMethod.GET)
    public TyResponse getVerificationCodes(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize);

    /**
     * 查询所有代充信息
     *
     * @return
     */
    @RequestMapping(value = "/recharge/page/index={pageIndex}&size={pageSize}", method = RequestMethod.GET)
    public TyResponse getRecharges(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize);

    /**
     * 查询所有代理操作
     *
     * @return
     */
    @RequestMapping(value = "/proxyopearte/page/index={pageIndex}&size={pageSize}", method = RequestMethod.GET)
    public TyResponse getProxyOperates(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize);

    /**
     * 查询所有销售统计
     *
     * @return
     */
    @RequestMapping(value = "/salestatistic", method = RequestMethod.POST)
    public TyResponse getSaleStatistics(@RequestBody SaleStatisticsRequest request);

    /**
     * 查询所有销售明细
     *
     * @return
     */
    @RequestMapping(value = "/saledetail", method = RequestMethod.POST)
    public TyResponse getSaleDetails(@RequestBody SaleStatisticsRequest request);

    /**
     * 查询所有亏损返利
     *
     * @return
     */
    @RequestMapping(value = "/lossrabate/page/index={pageIndex}&size={pageSize}", method = RequestMethod.GET)
    public TyResponse getLossRebates(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize);

    /**
     * 查询亏损返利
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/lossrabate", method = RequestMethod.POST)
    public TyResponse getLossRebates(@RequestBody LossRebateRequest request);


    /**
     * 查询排行榜奖励
     *
     * @return
     */
    @RequestMapping(value = "/chartsrewards/page/index={pageIndex}&size={pageSize}", method = RequestMethod.GET)
    public TyResponse getChartsRewards(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize);

    /**
     * 查询排行榜奖励
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/chartsrewards/", method = RequestMethod.POST)
    public TyResponse getChartsRewards(@RequestBody ChartsRewardPageRequest request);


    /**
     * 查询排行榜奖励
     *
     * @return
     */
    @RequestMapping(value = "/vchartsrewards/page/index={pageIndex}&size={pageSize}", method = RequestMethod.GET)
    public TyResponse getVChartsRewards(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize);

    /**
     * 查询排行榜奖励
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/vchartsrewards/", method = RequestMethod.POST)
    public TyResponse getVChartsRewards(@RequestBody VChartsRewardPageRequest request);


    /**
     * 查询投注工资
     *
     * @return
     */
    @RequestMapping(value = "/chipinwage", method = RequestMethod.POST)
    public TyResponse getChipinWages(@RequestBody ChipinWagePageRequest request);

    /**
     * 查询下线提成
     *
     * @return
     */
    @RequestMapping(value = "/juniorcommission", method = RequestMethod.POST)
    public TyResponse getJuniorCommissions(@RequestBody JuniorCommissionsPageRequest request);

    /**
     * 查询首冲返利
     *
     * @return
     */
    @RequestMapping(value = "/firstchargerebate/", method = RequestMethod.GET)
    public TyResponse getFirstchargeRebates();

    /**
     * 查询首冲返利
     *
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value = "/firstchargerebate/{id}&{type}", method = RequestMethod.GET)
    public TyResponse getFirstchargeRebates(@PathVariable("id") String id, @PathVariable("type") String type);

    /**
     * 查询每日统计
     *
     * @return
     */
    @RequestMapping(value = "/dailystatistic", method = RequestMethod.GET)
    public TyResponse getDailyStatistic();

    /**
     * 管理员登录
     */
    @RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
    public TyResponse getAdminLogins(@RequestBody TyPageEntity pageEntity);

//    /**
//     * 查询会员登录
//     */
//    @RequestMapping(value = "/memberlogin/page/index={pageIndex}&size={pageSize}", method = RequestMethod.GET)
//    public IPage<Map<String, Object>> getMemberLogins(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize);
//
//    /**@RequestBody HashMap<String,ActivitiBpm>
//     * 查询会员登录
//     * @param contiditon
//     * @return
//     */
//    @RequestMapping(value = "/memberlogin", method = RequestMethod.POST)
//    public IPage<Map<String, Object>> getMemberLogins(@RequestBody PageConditionEntity entity);

    @RequestMapping(value = "/memberlogin", method = RequestMethod.POST)
    public TyResponse getMemberLogins(@RequestBody MemberLoginRequest request);
}
