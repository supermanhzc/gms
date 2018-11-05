package com.taoyuan.gms.core.admin.bo;

import lombok.Data;

@Data
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
