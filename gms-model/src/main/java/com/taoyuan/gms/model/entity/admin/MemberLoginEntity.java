package com.taoyuan.gms.model.entity.admin;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.quartz.utils.counter.sampled.TimeStampedCounterValue;

import java.sql.Timestamp;

/**
 * 会员登陆
 */
@Data
@ToString
@EqualsAndHashCode
@TableName(value = "admin_memberlogin")
public class MemberLoginEntity {
    private Long id;

    //ID
    private Long memberId;

    //昵称
    private String memberNickName;

    //状态 1:成功 2:失败
    private int status;

    //IP
    private String ip;

    //地址
    private String addr;

    //时间
    private Timestamp time;
}
