package com.taoyuan.gms.api.admin.contentmgnt;

import lombok.Data;

@Data
public class AnnouncementDto {
    private long id;

    private String title;

    private String sort;

    private String content;
}
