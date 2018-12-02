package com.taoyuan.gms.model.dto.admin.statistic;

import lombok.Data;

/**
 * 日统计
 */
@Data
public class DailyStatisticResponse {
    private String date;

    //注册会员：当天的注册数量
    private int registerMemberNum;

    //会员余额：会员总余额
    private String membersBalance;

    //代理余额：代理账户的总余额
    private String proxyBalance;

    //游戏盈亏：普通会员参与游戏的总盈亏
    private String gameProfitLoss;

    //兑奖额：会员兑换奖品的数额
    private String exchange;

    //代充额：代理为会员代充的金额
    private String  substitute;

    //兑奖手续费：会员兑奖支出的手续费
    private String exchangePoundage;

    //卡密充值：会员使用卡密充值的数额
    private String cdKeyRecharge;

    //排行奖：被领取的排行榜奖励
    private String  chartsReward;

    //投注提成：发放给上线的投注提成
    private String chipinCommission;

    //首充奖励：被领取的首充奖励
    private String firstChargeRebate;

    //亏损返利：被领取的亏损返利
    private String  lossRebate;

    //投注工资：被领取的投注工资
    private String chipinWage;
}
