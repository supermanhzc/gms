package com.taoyuan.gms.model.entity.statistic;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@TableName(value = "v_ty_today_statistics")
public class TodayStatisticsEntity {
    private String today;
    private int userCount;
    private Double userBalance;
    private Double proxyBalance;
    private Double gameBalance;
    private Double cashBalance;
    private Double rechargBalance;
    private Double cashCost;
    private Double cardBalance;
    private Double rankingBalance;
    private Double bettingCommission;
    private Double  firstReward;
    private Double  lossRebate;
    private Double  bettingWage;
}
