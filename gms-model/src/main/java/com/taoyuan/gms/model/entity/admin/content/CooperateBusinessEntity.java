package com.taoyuan.gms.model.entity.admin.content;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "admin_cooperatebusiness")
public class CooperateBusinessEntity implements Serializable {

    private Long id;

    //名称
    private String name;

    //QQ
    private String qq;

    private Long createUser;

    private Date createTime;

    private Long updateUser;

    private Date updateTime;

}
