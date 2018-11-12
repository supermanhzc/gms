package com.taoyuan.gms.model.entity.admin;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@EqualsAndHashCode
@TableName(value = "admin_lossrabate")
public class LossRabateEntity {

    private Long id;

    private Long memberId;

    private String memberNickName;

    private Double loss;

    private Double rabate;

    private Date time;
}
