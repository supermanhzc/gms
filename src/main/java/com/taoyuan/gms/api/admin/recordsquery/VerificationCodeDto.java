package com.taoyuan.gms.api.admin.recordsquery;

import lombok.Data;

@Data
public class VerificationCodeDto {
    private long id;

    private String infName;

    private String type;

    private String time;
}
