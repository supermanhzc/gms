package com.taoyuan.gms.api.adminmanage.content;

import lombok.Data;

@Data
public class AnnouncementDto {
    private long id;

    //标题
    private String title;

    //排序
    private String sort;

    //内容
    private String content;
}
