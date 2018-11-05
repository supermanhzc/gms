package com.taoyuan.gms.api.adminmanage.recordsquery;

import lombok.Data;

@Data
public class MemberLoginDto {
    private String id;

    private String nickName;

    private String status;

    private String ip;

    private String addr;

    private String time;
}
