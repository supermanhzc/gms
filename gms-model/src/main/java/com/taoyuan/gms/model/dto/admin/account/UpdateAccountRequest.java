package com.taoyuan.gms.model.dto.admin.account;

import lombok.Data;

@Data
public class UpdateAccountRequest extends AccountRequest {
    private String name;
    private String qq;
}
