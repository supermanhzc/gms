package com.taoyuan.gms.model.entity.admin.records;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 销售明细
 */
@Data
@TableName(value = "admin_saledetail")
public class SaleDetailEntity {

    private Long id;

    //代理ID
    private Long proxyId;

    //代理名称
    private String proxyName;

    //时间
    private Date time;

    //代充金额
    private double substituteMoney;

    //回收金额
    private double callbackMoney;

    //收益
    private double income;
}
