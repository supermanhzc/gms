package com.taoyuan.gms.model.entity.admin.web;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "admin_vchartrankingsetting")
public class VChartRankingSettingEntity {
    private Long id;

    private int rankingCount;

    private int baseChart;
}
