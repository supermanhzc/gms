package com.taoyuan.gms.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.gms.model.entity.admin.PageConditionEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Api(value = "记录查询")
@RequestMapping("/records")
public interface RecordsQueryApi {
    /**
     * 查询所有验证码信息
     *
     * @return
     */
    @RequestMapping(value = "/verificationcode/page/index={pageIndex}&size={pageSize}", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getVerificationCodes(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize);

    /**
     * 查询所有代充信息
     *
     * @return
     */
    @RequestMapping(value = "/substitute/page/index={pageIndex}&size={pageSize}", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getSubstitutes(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize);

    /**
     * 查询所有代理操作
     *
     * @return
     */
    @RequestMapping(value = "/proxyopearte/page/index={pageIndex}&size={pageSize}", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getProxyOperates(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize);

    /**
     * 查询所有销售统计
     *
     * @return
     */
    @RequestMapping(value = "/salestatistic/page/index={pageIndex}&size={pageSize}", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getSaleStatistics(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize);

    /**
     * 查询所有销售明细
     *
     * @return
     */
    @RequestMapping(value = "/saledetail/", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getSaleDetails(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize);

    /**
     * 查询所有销售明细
     *
     * @param name  代理名称
     * @param start 起始日期
     * @param end   结束日期
     * @return
     */
    @RequestMapping(value = "/saledetail/{name}&{start}&{end}", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getSaleDetail(@PathVariable("name") String name, @PathVariable("start") String start, @PathVariable("end") String end);

    /**
     * 查询所有亏损返利
     *
     * @return
     */
    @RequestMapping(value = "/lossrabate/page/index={pageIndex}&size={pageSize}", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getLossRebates(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize);

    /**
     * 查询亏损返利
     * @param pageConditionEntity
     * @return
     */
    @RequestMapping(value = "/lossrabate/condition", method = RequestMethod.POST)
    public IPage<Map<String, Object>> getLossRebates(@RequestBody PageConditionEntity pageConditionEntity);


    /**
     * 查询排行榜奖励
     *
     * @return
     */
    @RequestMapping(value = "/chartsrewards/page/index={pageIndex}&size={pageSize}", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getChartsRewards(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize);

    /**
     * 查询排行榜奖励
     *
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value = "/chartsreward/{id}&{type}", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getChartsRewards(@PathVariable("id") String id, @PathVariable("type") String type);


    /**
     * 查询排行榜奖励
     *
     * @return
     */
    @RequestMapping(value = "/vchartsrewards/page/index={pageIndex}&size={pageSize}", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getVChartsRewards(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize);

    /**
     * 查询排行榜奖励
     *
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value = "/vchartsreward/{id}&{type}", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getVChartsRewards(@PathVariable("id") String id, @PathVariable("type") String type);



    /**
     * 查询投注工资
     *
     * @return
     */
    @RequestMapping(value = "/chipinwage/page/index={pageIndex}&size={pageSize}", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getChipinWages(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize);

    /**
     * 查询投注工资
     *
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value = "/chipinwage/{id}&{type}", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getChipinWages(@PathVariable("id") String id, @PathVariable("type") String type);

    /**
     * 查询下线提成
     *
     * @return
     */
    @RequestMapping(value = "/juniorcommission/", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getJuniorCommissions();

    /**
     * 查询下线提成
     *
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value = "/juniorcommission/{id}&{type}", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getJuniorCommissions(@PathVariable("id") String id, @PathVariable("type") String type);

    /**
     * 查询首冲返利
     *
     * @return
     */
    @RequestMapping(value = "/firstchargerebate/", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getFirstchargeRebates();

    /**
     * 查询首冲返利
     *
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value = "/firstchargerebate/{id}&{type}", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getFirstchargeRebates(@PathVariable("id") String id, @PathVariable("type") String type);

    /**
     * 查询每日统计
     *
     * @param date
     * @return
     */
    @RequestMapping(value = "/dailystatistic/{date}", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getDailyStatistic(@PathVariable("date") String date);

    /**
     * 查询会员登录
     */
    @RequestMapping(value = "/memberlogin/page/index={pageIndex}&size={pageSize}", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getMemberLogins(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize);

    /**
     * 查询会员登录
     *
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value = "/memberlogin/{id}&{type}", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getMemberLogins(@PathVariable("id") String id, @PathVariable("type") String type);
}
