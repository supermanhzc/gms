package com.taoyuan.gms.core.proxymanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.bs.controller.TyBaseController;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.framework.common.util.TyRandomUtil;
import com.taoyuan.gms.api.proxy.CardPwdInventoryApi;
import com.taoyuan.gms.common.util.CardUtil;
import com.taoyuan.gms.core.adminmanage.dao.CardPasswordMapper;
import com.taoyuan.gms.core.adminmanage.service.ICardPasswordService;
import com.taoyuan.gms.model.dto.admin.CardPwdInventoryResquest;
import com.taoyuan.gms.model.entity.admin.CardPasswordEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class CardPwdInventoryController extends TyBaseController implements CardPwdInventoryApi {

    @Autowired
    private ICardPasswordService service;

    @Autowired
    private CardPasswordMapper mapper;

    @Override
    public TyResponse retrieve(CardPwdInventoryResquest resquest) {
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
    public TyResponse create(Map<String, Object> map) {
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

        //TODO 更新金额

        return new TySuccessResponse(entityList);
    }

    public BigDecimal getBalance(Long id) {
        //TODO 此处逻辑先屏蔽，后面等功能提供后放开
//        QueryWrapper<UserEntity> wrapper = new QueryWrapper<UserEntity>();
//        wrapper.lambda().eq(UserEntity::getId, id);
//        UserEntity user = userService.getOne(wrapper);
        return BigDecimal.valueOf(1000000);
    }

    //TODO 此处需要调用接口更新用户余额

    /**
     * 更新用户余额
     *
     * @param id
     * @param money
     */
    public void updateBalance(Long id, BigDecimal money) {
        BigDecimal balance = getBalance(id).subtract(money);

        //TODO 此处接口实现后放开
//        QueryWrapper<UserEntity> wrapper = new QueryWrapper<UserEntity>();
//        wrapper.lambda().eq(UserEntity::getId, id);
//        UserEntity user = userService.getOne(wrapper);
    }

}