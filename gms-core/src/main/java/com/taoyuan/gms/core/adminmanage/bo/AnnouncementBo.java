package com.taoyuan.gms.core.adminmanage.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "admin_announcement")
public class AnnouncementBo {

    private long id;

    private String title;

    private String sort;

    private String content;

    private Long createUser;

    private String createTime;

    private Long updateUser;

    private String updateTime;
}
