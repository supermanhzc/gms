package com.taoyuan.gms.model.entity.admin.content;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@EqualsAndHashCode
@TableName(value = "admin_announcement")
public class AnnouncementEntity implements Serializable{
    private Long id;

    //标题
    private String title;

    //排序
    private String sort;

    //内容
    private String content;

    private Long createUser;

    private Date createTime;

    private Long updateUser;

    private Date updateTime;
}
