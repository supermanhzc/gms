package com.taoyuan.gms.model.entity.admin.prize;

import lombok.Data;

import java.io.Serializable;

@Data
public class PrizeClassifyEntity implements Serializable {

    private long id;

    //奖品分类名称
    private String name;
}
