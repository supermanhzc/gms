package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.framework.common.util.TyRandomUtil;
import com.taoyuan.gms.api.admin.CardPasswordApi;
import com.taoyuan.gms.core.adminmanage.service.ICardPasswordService;
import com.taoyuan.gms.model.entity.admin.CardPasswordEntity;
import com.taoyuan.gms.model.entity.admin.web.CardTypeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class CardPasswordController extends BaseController implements CardPasswordApi {

    @Autowired
    private ICardPasswordService service;

    @Override
    public IPage<Map<String, Object>> retrieve(Map<String, Object> map) {
        Page page = getPage(map);

        QueryWrapper<CardPasswordEntity> wrapper = new QueryWrapper<CardPasswordEntity>();
        if (map.containsKey("key")) {
            wrapper.lambda().eq(CardPasswordEntity::getCardType, map.get("cardType"));
        }
        if (map.containsKey("cardId")) {
            wrapper.lambda().eq(CardPasswordEntity::getCardId, map.get("cardId"));
        }

        return service.pageMaps(page, wrapper);
    }

    @Override
    public List<CardPasswordEntity> create(Map<String, Object> map) {
        if (!map.containsKey("cardType")) {
            throw new ValidateException("卡类型不能为空。");
        }
        if (!map.containsKey("number")) {
            throw new ValidateException("数量不能为空。");
        }

        int cardType = (int) map.get("cardType");
        String cardHead = getCardHead(cardType);
        int number = (int) map.get("number");

        List<CardPasswordEntity> entityList = new ArrayList<CardPasswordEntity>();
        for (int i = 0; i < number; i++) {
            //默认生成随机8位id和密码
            String id = TyRandomUtil.getRandomNum(8);
            log.info("generate id is {}",id);
            String pwd = TyRandomUtil.getRandomStr(8);
            log.info("generate password is {}",pwd);
            CardPasswordEntity entity = new CardPasswordEntity();
            entity.setCardType(cardType);
            entity.setCardId(cardHead+id);
            entity.setStatus(1);
            entity.setCardPassword(pwd);
            entity.setCreateTime(new Date());
            entity.setCreateUser(TySession.getCurrentUser().getUserId());
            entity.setOwner(TySession.getCurrentUser().getName());
            entity.setStartTime(new Date());
            entityList.add(entity);
        }
        service.saveBatch(entityList);
        return entityList;
    }

    @Override
    public TyResponse withdraw(Map<String, Object> map) {
        if(!map.containsKey("cardId")){
            throw new ValidateException("卡号不能为空。");
        }

        String cardId = (String) map.get("cardId");
        CardPasswordEntity entity = new CardPasswordEntity();
//        CardPasswordEntity entity = service.getOne(new QueryWrapper<CardPasswordEntity>().eq("card_id",cardId));
        log.info("withdraw object is {}",entity);
        entity.setStatus(3);
        service.update(entity,new QueryWrapper<CardPasswordEntity>().eq("card_id",cardId));
        return new TySuccessResponse(cardId);
    }

    @Override
    public TyResponse delete(Map<String, Object> map) {
        if(!map.containsKey("cardId")){
            throw new ValidateException("卡号不能为空。");
        }

        String cardId = (String) map.get("cardId");
        service.remove(new QueryWrapper<CardPasswordEntity>().eq("card_id",cardId));
        return new TySuccessResponse(cardId);
    }

    private String getCardHead(int cardType) {
        String cardHead = null;
        switch (cardType) {
            case 1:
                cardHead = "10y";
                break;
            case 2:
                cardHead = "20y";
                break;
            case 3:
                cardHead = "30y";
                break;
            case 4:
                cardHead = "redzuan";
                break;
            default:
                throw  new ValidateException("不支持的卡类型。");
        }
        return cardHead;
    }

    private String getRandomValue(int length, String randomValue) {
        QueryWrapper<CardPasswordEntity> wrapper = new QueryWrapper<CardPasswordEntity>();
        List<CardPasswordEntity> dbValue = service.list(wrapper);
        for (CardPasswordEntity entity : dbValue) {
            if (entity.getCardId().equals(getCardHead(entity.getCardType())+randomValue)) {
                randomValue = TyRandomUtil.getRandomNum(length);
                return getRandomValue(length, randomValue);
            }
        }

        return randomValue;
    }
}
