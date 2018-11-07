package com.taoyuan.gms.model.dto.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Api(value = "公告服务")
@RequestMapping("/records")
public interface RecordsQueryApi {
    /**
     * 查询所有验证码信息
     *
     * @return
     */
    @RequestMapping(value = "/verificationcode/", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getVerificationCodes();

    /**
     * 查询所有验证码信息
     *
     * @return
     */
    @RequestMapping(value = "/substitute/", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getSubstitutes();

    /**
     * 查询所有代理操作
     *
     * @return
     */
    @RequestMapping(value = "/proxyopearte/", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getProxyOperates();

    /**
     * 查询所有销售统计
     *
     * @return
     */
    @RequestMapping(value = "/salestatistic/", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getSaleStatistics();

    /**
     * 查询所有销售明细
     *
     * @return
     */
    @RequestMapping(value = "/saledetail/", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getSaleDetails();

    /**
     * 查询所有销售明细
     *
     * @param name  代理名称
     * @param start 起始日期
     * @param end   结束日期
     * @return
     */
    @RequestMapping(value = "/saledetail/{name}&{start}&{end}", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getSaleDetail(@PathVariable("name") String name, @PathVariable("start") String start, @PathVariable("end")String end);

    /**
     * 查询所有亏损返利
     *
     * @return
     */
    @RequestMapping(value = "/lossrabate/", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getLossRebates();

    /**
     * 查询亏损返利
     *
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value = "/lossrabate/{id}&{type}", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getLossRebates(@PathVariable("id") String id, @PathVariable("type") String type);


    /**
     * 查询排行榜奖励
     *
     * @return
     */
    @RequestMapping(value = "/lossrabate/", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getChartsRewards();

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
     * 查询投注工资
     *
     * @return
     */
    @RequestMapping(value = "/chipinwage/", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getChipinWages();

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
     * @param date
     * @return
     */
    @RequestMapping(value = "/dailystatistic/{date}", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getDailyStatistic(@PathVariable("date") String date);

    /**
     * 查询会员登录
     */
    @RequestMapping(value = "/memberlogin", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getMemberLogins();

    /**
     * 查询会员登录
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value = "/memberlogin/{id}&{type}", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getMemberLogins(@PathVariable("id") String id, @PathVariable("type") String type);
}
