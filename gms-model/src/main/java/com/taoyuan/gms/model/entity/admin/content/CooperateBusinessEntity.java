package com.taoyuan.gms.model.entity.admin.content;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "admin_cooperatebusiness")
public class CooperateBusinessEntity implements Serializable {

    private long id;

    //名称
    private String name;

    //QQ
    private String qq;

    private Long createUser;

    private String createTime;

    private Long updateUser;

    private String updateTime;

}
