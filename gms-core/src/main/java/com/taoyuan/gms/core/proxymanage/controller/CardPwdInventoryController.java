package com.taoyuan.gms.core.proxymanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.framework.common.util.TyRandomUtil;
import com.taoyuan.gms.api.proxy.CardPwdInventoryApi;
import com.taoyuan.gms.common.util.CardUtil;
import com.taoyuan.gms.core.adminmanage.dao.CardPasswordMapper;
import com.taoyuan.gms.core.adminmanage.service.ICardPasswordService;
import com.taoyuan.gms.model.dto.admin.card.CardPwdInventoryResquest;
import com.taoyuan.gms.model.entity.admin.card.CardPasswordEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class CardPwdInventoryController extends BaseGmsProxyController implements CardPwdInventoryApi {

    @Autowired
    private ICardPasswordService service;

    @Autowired
    private CardPasswordMapper mapper;

    @Override
    public TyResponse retrieve(@RequestBody CardPwdInventoryResquest resquest) {
        log.info("input:{}", resquest);
        Page page = getPage(resquest);

        QueryWrapper<CardPasswordEntity> wrapper = new QueryWrapper<CardPasswordEntity>();
        if (null != resquest.getKeyword()) {
            String keyword = (String) resquest.getKeyword();
            wrapper.lambda().eq(CardPasswordEntity::getCardId, keyword).or().eq(CardPasswordEntity::getCardPassword,
                    keyword);
        }

        if (0 != resquest.getCardType()) {
            wrapper.lambda().eq(CardPasswordEntity::getCardType, resquest.getCardType());
        }
        wrapper.lambda().eq(CardPasswordEntity::getStatus, 1).or().eq(CardPasswordEntity::getStatus, 2).eq(CardPasswordEntity::getOwner, getCurrentUserName()).orderByDesc(CardPasswordEntity::getCreateTime);

        return  new TySuccessResponse(mapper.selectPage(page,wrapper));
    }

    @Override
    public TyResponse create(@RequestBody Map<String, Object> map) {
        if (!map.containsKey("cardType")) {
            throw new ValidateException("卡类型不能为空。");
        }
        int cardType = (int) map.get("cardType");
        String cardHead = CardUtil.getCardHead(cardType);

        if (!map.containsKey("number")) {
            throw new ValidateException("数量不能为空。");
        }
        int number = (int) map.get("number");
        BigDecimal total = CardUtil.getMoney(cardType).multiply(BigDecimal.valueOf(number));
        if (total.compareTo(getBalance(getCurrentUserId())) > 0) {
            throw new ValidateException("生成卡密总额大于用户余额。");
        }

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
            entity.setCreateUser(getCurrentUserId());
            entity.setOwner(getCurrentUserName());
            entity.setStartTime(new Date());
            entityList.add(entity);
        }
        service.saveBatch(entityList);

        //记录日志
        recordOperation(7,"创建卡密",BigDecimal.ZERO);
        return new TySuccessResponse(entityList);
    }
}
