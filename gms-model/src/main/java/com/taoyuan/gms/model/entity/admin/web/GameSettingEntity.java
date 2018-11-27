package com.taoyuan.gms.model.entity.admin.web;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "admin_gamesetting")
public class GameSettingEntity {
    private Long id;

    //游戏名称
    private String gameName;

    //投注下限
    private double chipinLower;

    //投注上限
    private double chipinUpper;

    //停止投注时间
    private double stopChipinTime;

    //开奖监控值
    private double drawMonitorValue;

    //返奖率
    private double returnRate;

    //开始游戏，1开启，0关闭
    private int openGame;

    //游戏开奖，1开启，0关闭
    private int drawGame;

    //计算流水，1开启，0关闭
    private int computeTurnover;

    //流水号码数
    private int turnoverNumCount;

    public void update(GameSettingEntity other){
        setChipinLower(0==other.getChipinLower()?0:other.getChipinLower());
        setChipinUpper(0==other.getChipinUpper()?0:other.getChipinUpper());
        setComputeTurnover(0==other.getComputeTurnover()?0:other.getComputeTurnover());
        setDrawGame(0==other.getDrawGame()?0:other.getDrawGame());
        setDrawMonitorValue(0==other.getDrawMonitorValue()?0:other.getDrawMonitorValue());
        setOpenGame(0==other.getOpenGame()?0:other.getOpenGame());
        setReturnRate(0==other.getReturnRate()?0:other.getReturnRate());
        setStopChipinTime(0==other.getStopChipinTime()?0:other.getStopChipinTime());
        setTurnoverNumCount(0==other.getTurnoverNumCount()?0:other.getTurnoverNumCount());
    }
}
