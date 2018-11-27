package com.taoyuan.gms.model.entity.admin.prize;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName(value = "admin_prize")
public class PrizeEntity implements Serializable {

    private Long id;

    //奖品名称
    private String name;

    //奖品类目,从创建的类目中选择一个
    private String classify;

    //自动发货 1:是，0:否
    private int autoDispatch;

    //卡密类型 1:10元充值卡，2:20元充值卡，3:30元充值卡，4:红钻会员卡
    private int cardType;

    //基准价格
    private BigDecimal basicPrice;

    //库存
    private int stock;

    //图片
    private String icon;

    //已兑出，默认为0
    private int converted;

    //详情
    private String details;

    public void update(PrizeEntity prizeEntity) {
        if(null==prizeEntity){
            return;
        }

        if(null!=prizeEntity.getName()) {
            setName(prizeEntity.getName());
        }

        if(null!=prizeEntity.getClassify()) {
            setClassify(prizeEntity.getClassify());
        }

        if(!prizeEntity.getBasicPrice().equals(BigDecimal.ZERO)) {
            setBasicPrice(prizeEntity.getBasicPrice());
        }

        if(null!=prizeEntity.getIcon()) {
            setIcon(prizeEntity.getIcon());
        }

        if(null!=prizeEntity.getDetails()) {
            setDetails(prizeEntity.getDetails());
        }

        if(prizeEntity.getCardType()!=0){
            setCardType(prizeEntity.getCardType());
        }

        if(prizeEntity.getAutoDispatch()!=0){
            setAutoDispatch(prizeEntity.getAutoDispatch());
        }

        if(prizeEntity.getConverted()!=0){
            setConverted(prizeEntity.getConverted());
        }

        if(prizeEntity.getStock()!=0){
            setStock(prizeEntity.getStock());
        }
    }
}
