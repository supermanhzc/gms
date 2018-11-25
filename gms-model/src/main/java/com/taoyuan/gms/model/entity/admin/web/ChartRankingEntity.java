package com.taoyuan.gms.model.entity.admin.web;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "admin_chartranking")
public class ChartRankingEntity {
    private Long id;

    private int ranking;

    private int chart;
}
