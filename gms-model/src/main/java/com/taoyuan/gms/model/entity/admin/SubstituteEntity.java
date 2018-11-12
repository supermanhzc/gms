package com.taoyuan.gms.model.entity.admin;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * 代充记录
 */
@Data
@ToString
@EqualsAndHashCode
@TableName(value = "admin_substitute")
public class SubstituteEntity {
    private Long id;

    private Long proxyId;

    private String proxyName;

    private Long memberId;

    private String memberNickName;

    private Double money;

    private Timestamp time;

    private String status;
}
