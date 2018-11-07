package com.taoyuan.gms.api.adminmanage.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "admin_cooperatebusiness")
public class CooperateBusinessBo {
    private long id;

    private String name;

    private String qq;

    private Long createUser;

    private String createTime;

    private Long updateUser;

    private String updateTime;
}
