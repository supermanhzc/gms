package com.taoyuan.gms.model.entity.admin.content;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.taoyuan.framework.common.http.JsonDateSerializer;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Long updateUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
