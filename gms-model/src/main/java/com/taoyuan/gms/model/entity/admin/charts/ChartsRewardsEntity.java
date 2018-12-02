package com.taoyuan.gms.model.entity.admin.charts;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 排行榜奖励
 */
@Data
@ToString
@EqualsAndHashCode
@TableName(value = "admin_chartsrewards")
public class ChartsRewardsEntity implements Serializable {

    private Long id;

    //类型 1:排行榜奖励，2:虚拟排行榜奖励
    private int type;

    //玩家ID
    private Long memberId;

    //名称
    private String memberNickName;

    //奖励
    private Double rewards;

    //日期
    private Date time;

    //状态：未领取、已领取、已逾期
    private String status;
}
