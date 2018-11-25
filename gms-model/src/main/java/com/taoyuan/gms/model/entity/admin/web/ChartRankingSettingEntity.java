package com.taoyuan.gms.model.entity.admin.web;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "admin_chartrankingsetting")
public class ChartRankingSettingEntity {
    private Long id;

    private int rankingCount;
}
