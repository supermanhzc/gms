package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.admin.PrizeApi;
import com.taoyuan.gms.core.adminmanage.service.IPrizeService;
import com.taoyuan.gms.model.entity.admin.prize.PrizeEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class PrizeController extends BaseGmsController implements PrizeApi {

    @Autowired
    private IPrizeService service;

    @Override
    public TyResponse getPrizes() {
        return new TySuccessResponse(service.list(null));
    }

    @Override
    public TyResponse getPrize(Long id) {
        if(null==id){
            throw new ValidateException("id不能为空。");
        }

        return new TySuccessResponse(service.getById(id));
    }

    @Override
    public TyResponse createPrize(PrizeEntity prizeEntity) {
        if(StringUtils.isEmpty(prizeEntity.getName())){
            throw new ValidateException("名称不能为空。");
        }

        if(StringUtils.isEmpty(prizeEntity.getClassify())){
            throw new ValidateException("类目不能为空。");
        }

        if(prizeEntity.getCardType()<1||prizeEntity.getCardType()>4){
            throw new ValidateException("卡类型非法。");
        }

        if(prizeEntity.getBasicPrice().equals(BigDecimal.ZERO)){
            throw new ValidateException("基准价格不能为0。");
        }

        if(StringUtils.isEmpty(prizeEntity.getDetails())){
            throw new ValidateException("详情不能为空。");
        }

        if(StringUtils.isEmpty(prizeEntity.getIcon())){
            throw new ValidateException("图片不能为空。");
        }

        service.save(prizeEntity);
        return new TySuccessResponse(prizeEntity);
    }

    @Override
    public TyResponse modifyPrize(PrizeEntity prizeEntity) {
        if(null==prizeEntity.getId()){
            throw new ValidateException("id不能为空。");
        }

        Long id = prizeEntity.getId();
        PrizeEntity dbValue = service.getById(id);
        dbValue.update(prizeEntity);
        service.saveOrUpdate(dbValue);
        return new TySuccessResponse(dbValue);
    }

    @Override
    public TyResponse deletePrize(Long id) {
        service.remove(new QueryWrapper<PrizeEntity>().lambda().eq(PrizeEntity::getId,id));
        return new TySuccessResponse(id);
    }
}
