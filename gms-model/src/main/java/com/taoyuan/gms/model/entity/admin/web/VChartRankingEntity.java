package com.taoyuan.gms.model.entity.admin.web;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "admin_vchartranking")
public class VChartRankingEntity {
    private Long id;

    //排名
    private int ranking;

    //奖励
    private int chart;
}
