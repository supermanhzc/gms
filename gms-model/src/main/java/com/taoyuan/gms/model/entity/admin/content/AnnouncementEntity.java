package com.taoyuan.gms.model.entity.admin.content;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
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

    private String createTime;

    private Long updateUser;

    private String updateTime;
}
