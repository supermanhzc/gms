package com.taoyuan.gms.model.entity.admin;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@ToString
@EqualsAndHashCode
@TableName(value = "admin_lossrabate")
public class LossRabateEntity {

    private Long id;

    private Long memberId;

    private String memberNickName;

    private BigDecimal loss;

    private BigDecimal rabate;

    private LocalDate time;

    //状态，1表示未领取，2表示领取，3表示已逾期
    private int status;
}
