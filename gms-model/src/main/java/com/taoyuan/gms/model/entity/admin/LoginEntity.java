package com.taoyuan.gms.model.entity.admin;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginEntity implements Serializable {
    private String id;

    private String type;

    private String ip;

    private String name;

    private String status;

    private String time;
}
