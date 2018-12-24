package com.taoyuan.gms.model.entity.admin.account;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taoyuan.framework.common.entity.TyBaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName(value = "gms_gold")
public class UserGoldEntity extends TyBaseEntity {
    private Long id;

    //用户id
    private Long userId;

    // 金币数量
    private BigDecimal gold;

    //游戏类型
    private int type;

    //时间
    private Date date;
}
