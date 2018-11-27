package com.taoyuan.gms.model.entity.admin.prize;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "admin_prizeclassify")
public class PrizeClassifyEntity implements Serializable {

    private Long id;

    //奖品分类名称
    private String name;
}
