package com.taoyuan.gms.model.dto.admin.account;

import lombok.Data;

@Data
public class RegisterAccountRequest extends AccountRequest {
    private Long refereeId;
    private String verificationCode;
    private String smsVerificationCode;
}
