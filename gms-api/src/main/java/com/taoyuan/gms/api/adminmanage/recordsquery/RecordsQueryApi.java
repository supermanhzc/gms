package com.taoyuan.gms.api.adminmanage.recordsquery;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(value = "公告服务")
@RequestMapping("/records")
public interface RecordsQueryApi {
    /**
     * 查询所有验证码信息
     *
     * @return
     */
    @RequestMapping(value = "/verificationcode/", method = RequestMethod.GET)
    public List<VerificationCodeDto> getVerificationCodes();

    /**
     * 查询所有验证码信息
     *
     * @return
     */
    @RequestMapping(value = "/substitute/", method = RequestMethod.GET)
    public List<SubstituteDto> getSubstitutes();

    /**
     * 查询所有代理操作
     *
     * @return
     */
    @RequestMapping(value = "/proxyopearte/", method = RequestMethod.GET)
    public List<ProxyOperateDto> getProxyOperates();

    /**
     * 查询所有销售统计
     *
     * @return
     */
    @RequestMapping(value = "/salestatistic/", method = RequestMethod.GET)
    public List<SaleStatisticDto> getSaleStatistics();

    /**
     * 查询所有销售明细
     *
     * @return
     */
    @RequestMapping(value = "/saledetail/", method = RequestMethod.GET)
    public List<SaleDetailDto> getSaleDetails();

    /**
     * 查询所有销售明细
     *
     * @param name  代理名称
     * @param start 起始日期
     * @param end   结束日期
     * @return
     */
    @RequestMapping(value = "/saledetail/{name}&{start}&{end}", method = RequestMethod.GET)
    public List<SaleDetailDto> getSaleDetail(String name, String start, String end);

    /**
     * 查询所有亏损返利
     *
     * @return
     */
    @RequestMapping(value = "/lossrabate/", method = RequestMethod.GET)
    public List<LossRebateDto> getLossRebates();

    /**
     * 查询亏损返利
     *
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value = "/lossrabate/{id}&{type}", method = RequestMethod.GET)
    public List<LossRebateDto> getLossRebates(String id, String type);


    /**
     * 查询排行榜奖励
     *
     * @return
     */
    @RequestMapping(value = "/lossrabate/", method = RequestMethod.GET)
    public List<ChartsRewardDto> getChartsRewards();

    /**
     * 查询排行榜奖励
     *
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value = "/chartsreward/{id}&{type}", method = RequestMethod.GET)
    public List<ChartsRewardDto> getChartsRewards(String id, String type);



    /**
     * 查询投注工资
     *
     * @return
     */
    @RequestMapping(value = "/chipinwage/", method = RequestMethod.GET)
    public List<ChipinWageDto> getChipinWages();

    /**
     * 查询投注工资
     *
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value = "/chipinwage/{id}&{type}", method = RequestMethod.GET)
    public List<ChipinWageDto> getChipinWages(String id, String type);

    /**
     * 查询下线提成
     *
     * @return
     */
    @RequestMapping(value = "/juniorcommission/", method = RequestMethod.GET)
    public List<JuniorCommissionDto> getJuniorCommissions();

    /**
     * 查询下线提成
     *
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value = "/juniorcommission/{id}&{type}", method = RequestMethod.GET)
    public List<JuniorCommissionDto> getJuniorCommissions(String id, String type);

    /**
     * 查询首冲返利
     *
     * @return
     */
    @RequestMapping(value = "/firstchargerebate/", method = RequestMethod.GET)
    public List<FirstchargeRebateDto> getFirstchargeRebates();

    /**
     * 查询首冲返利
     *
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value = "/firstchargerebate/{id}&{type}", method = RequestMethod.GET)
    public List<FirstchargeRebateDto> getFirstchargeRebates(String id, String type);

    /**
     * 查询每日统计
     * @param date
     * @return
     */
    @RequestMapping(value = "/dailystatistic/{date}", method = RequestMethod.GET)
    public List<DailyStatisticDto> getDailyStatistic(String date);

    /**
     * 查询会员登录
     */
    @RequestMapping(value = "/memberlogin", method = RequestMethod.GET)
    public List<MemberLoginDto> getMemberLogins();

    /**
     * 查询会员登录
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value = "/memberlogin/{id}&{type}", method = RequestMethod.GET)
    public List<MemberLoginDto> getMemberLogins(String id, String type);
}
