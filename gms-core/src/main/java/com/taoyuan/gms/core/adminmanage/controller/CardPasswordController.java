package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.aaa.service.TyUserService;
import com.taoyuan.framework.common.entity.TyUser;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.framework.common.util.TyRandomUtil;
import com.taoyuan.gms.api.admin.CardPasswordApi;
import com.taoyuan.gms.core.adminmanage.service.ICardPasswordService;
import com.taoyuan.gms.model.entity.admin.CardPasswordEntity;
import com.taoyuan.gms.model.entity.admin.web.CardTypeEntity;
import com.taoyuan.gms.model.entity.proxy.CardPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    private TyUserService userService;

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
            log.info("generate id is {}", id);
            String pwd = TyRandomUtil.getRandomStr(8);
            log.info("generate password is {}", pwd);
            CardPasswordEntity entity = new CardPasswordEntity();
            entity.setCardType(cardType);
            entity.setCardId(cardHead + id);
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
        if (!map.containsKey("cardId")) {
            throw new ValidateException("卡号不能为空。");
        }

        String cardId = (String) map.get("cardId");
        CardPasswordEntity entity = new CardPasswordEntity();
//        CardPasswordEntity entity = service.getOne(new QueryWrapper<CardPasswordEntity>().eq("card_id",cardId));
        log.info("withdraw object is {}", entity);
        entity.setStatus(3);
        service.update(entity, new QueryWrapper<CardPasswordEntity>().eq("card_id", cardId));
        return new TySuccessResponse(cardId);
    }

    @Override
    public TyResponse withdrawbatch(List<CardPassword> cardPasswordList) {
        if (CollectionUtils.isEmpty(cardPasswordList)) {
            return new TySuccessResponse(null);
        }

        List<String> cardIdList = new ArrayList<String>();
        for (CardPassword cp : cardPasswordList) {
            String cardId = cp.getCardId();
            if (StringUtils.isEmpty(cardId)) {
                throw new ValidateException("待撤销卡密卡号不能为空。");
            }
            String pwd = cp.getPassword();
            if (StringUtils.isEmpty(pwd)) {
                throw new ValidateException("待撤销卡密密码不能为空。");
            }

            QueryWrapper<CardPasswordEntity> wrapper = new QueryWrapper<CardPasswordEntity>();
            wrapper.lambda().eq(CardPasswordEntity::getCardId, cardId).eq(CardPasswordEntity::getCardPassword, pwd);
            CardPasswordEntity dbValue = service.getOne(wrapper);
            if (null != dbValue) {
                cardIdList.add(cardId);
            }

        }

        log.info("card id list is {}", cardIdList);
        CardPasswordEntity entity = new CardPasswordEntity();
        entity.setStatus(3);
        service.update(entity, new QueryWrapper<CardPasswordEntity>().in("card_id", cardIdList));
        return new TySuccessResponse(cardIdList);
    }

    @Override
    public TyResponse delete(String id) {
        if (null == id) {
            throw new ValidateException("卡号不能为空。");
        }

        String cardId = id;
        log.info("card id is {}", cardId);
        service.remove(new QueryWrapper<CardPasswordEntity>().eq("card_id", cardId));
        return new TySuccessResponse(cardId);
    }

    @Override
    public List<CardPassword> getCardPasswordInfo(List<CardPassword> cardPasswordList) {
        log.info("input is {}", cardPasswordList);
        if (CollectionUtils.isEmpty(cardPasswordList)) {
            return cardPasswordList;
        }

        List<CardPassword> rsltList = new ArrayList<CardPassword>();
        List<String> cardIdList = new ArrayList<String>();
        for (CardPassword cp : cardPasswordList) {
            String cardId = cp.getCardId();
            if (StringUtils.isEmpty(cardId)) {
                throw new ValidateException("卡密卡号不能为空。");
            }
            if (cardIdList.contains(cardId)) {
                continue;
            }
            cardIdList.add(cardId);

            CardPasswordEntity dbValue = service.getCardPasswordById(cardId);
            if (null == dbValue) {
                cp.setInfo("卡号或密码不正确");
                rsltList.add(cp);
                continue;
            }

            //已回收
            if (dbValue.getStatus() == 3) {
                cp.setInfo("卡密已回收");
                rsltList.add(cp);
                continue;
            }
            String pwd = cp.getPassword();
            if (StringUtils.isEmpty(pwd.trim())) {
                cp.setInfo("卡号或密码不正确");
                rsltList.add(cp);
                continue;
            }

            if (pwd.equals(dbValue.getCardPassword())) {
                if (dbValue.getRechargeId() != null) {
                    String rechargeId = dbValue.getRechargeId();
                    TyUser user = userService.getUserById(Long.valueOf(rechargeId));
                    log.info("user info:{}", user);
                    cp.setRechargeId(rechargeId);
                    cp.setName(user.getName());
                    //TODO待增加
                    cp.setNickName("");
                    cp.setQq("");
                }
            } else {
                cp.setInfo("卡号或密码不正确");
            }
            rsltList.add(cp);
        }
        return rsltList;
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
                throw new ValidateException("不支持的卡类型。");
        }
        return cardHead;
    }

    private String getRandomValue(int length, String randomValue) {
        QueryWrapper<CardPasswordEntity> wrapper = new QueryWrapper<CardPasswordEntity>();
        List<CardPasswordEntity> dbValue = service.list(wrapper);
        for (CardPasswordEntity entity : dbValue) {
            if (entity.getCardId().equals(getCardHead(entity.getCardType()) + randomValue)) {
                randomValue = TyRandomUtil.getRandomNum(length);
                return getRandomValue(length, randomValue);
            }
        }

        return randomValue;
    }
}
