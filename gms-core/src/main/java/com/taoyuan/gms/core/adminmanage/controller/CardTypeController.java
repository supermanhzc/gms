package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.bs.aspect.OperControllerLog;
import com.taoyuan.framework.common.constant.StatusCode;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.framework.common.util.TyRandomUtil;
import com.taoyuan.gms.api.admin.CardTypeApi;
import com.taoyuan.gms.core.adminmanage.dao.CardTypeMapper;
import com.taoyuan.gms.core.adminmanage.service.ICardMgntService;
import com.taoyuan.gms.model.dto.admin.card.CardTypeRequest;
import com.taoyuan.gms.model.entity.admin.web.CardTypeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class CardTypeController extends BaseGmsController implements CardTypeApi {

    @Autowired
    private ICardMgntService service;

    @Autowired
    private CardTypeMapper cardTypeMapper;

    @Override
    @OperControllerLog(module = "卡类管理", type = "根据id查询卡类")
    public TyResponse retrieve(@PathVariable Long id) {
        QueryWrapper<CardTypeEntity> wrapper = new QueryWrapper<CardTypeEntity>();
        wrapper.lambda().eq(CardTypeEntity::getId, id);
        return new TySuccessResponse(service.getOne(wrapper));
    }

    @Override
    @OperControllerLog(module = "卡类管理", type = "根据卡类型和卡号查询卡类")
    public TyResponse retrieve(@RequestBody CardTypeRequest request) {
        Page page = getPage(request);

        QueryWrapper<CardTypeEntity> wrapper = new QueryWrapper<CardTypeEntity>();
        if (request.getCardType()!=0) {
            wrapper.lambda().eq(CardTypeEntity::getCardType, request.getCardType());
        }

        if (StringUtils.isEmpty(request.getCardId())) {
            wrapper.lambda().eq(CardTypeEntity::getCardId, request.getCardId());
        }

        return new TySuccessResponse(cardTypeMapper.selectMapsPage(page, wrapper));
    }

    @Override
    @OperControllerLog(module = "卡类管理", type = "创建卡类")
    public TyResponse create(@RequestBody CardTypeEntity cardTypeEntity) {
        validate(cardTypeEntity);

        int randomFigure = cardTypeEntity.getRandomFigure();
        String randomNumStr = getRandomValue(randomFigure, TyRandomUtil.getRandomNum(randomFigure));
        cardTypeEntity.setRandomNumStr(randomNumStr);
        cardTypeEntity.setCardId(cardTypeEntity.getCardHead() + randomNumStr);
        cardTypeEntity.setCreateUser(TySession.getCurrentUser().getUserId());
        cardTypeEntity.setCreateTime(new Date());
        service.saveOrUpdate(cardTypeEntity);
        return new TySuccessResponse(cardTypeEntity);
    }

    @Override
    @OperControllerLog(module = "卡类管理", type = "修改卡类")
    public TyResponse update(@RequestBody CardTypeEntity cardTypeEntity) {
        if (StringUtils.isEmpty(cardTypeEntity.getName())) {
            throw new ValidateException("名称不能为空。");
        }

        CardTypeEntity dbValue = service.getOne(new QueryWrapper<CardTypeEntity>().eq("id", cardTypeEntity.getId()));
        String randomNumStr = null;
        int randomFigure = cardTypeEntity.getRandomFigure();
        //用户修改位数并且与原值不相等则需要重新生成id
        if (cardTypeEntity.getRandomFigure() != 0 && cardTypeEntity.getRandomFigure() != dbValue.getRandomFigure()) {
            randomNumStr = getRandomValue(randomFigure, TyRandomUtil.getRandomNum(randomFigure));
        }
        dbValue.update(cardTypeEntity, randomNumStr);
        dbValue.setUpdateUser(TySession.getCurrentUser().getUserId());
        dbValue.setUpdateTime(new Date());
        service.saveOrUpdate(cardTypeEntity);
        return new TySuccessResponse(dbValue);
    }

    @Override
    @OperControllerLog(module = "卡类管理", type = "删除卡类")
    public TyResponse delete(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            throw new ValidateException(StatusCode.FAIL.getCode(), "id不能为空。");
        }

        service.remove(new QueryWrapper<CardTypeEntity>().eq("card_id", id));
        return new TySuccessResponse(id);
    }

    private String getRandomValue(int length, String randomValue) {
        QueryWrapper<CardTypeEntity> wrapper = new QueryWrapper<CardTypeEntity>();
        wrapper.lambda().eq(CardTypeEntity::getRandomFigure, length);
        List<CardTypeEntity> dbValue = service.list(wrapper);
        for (CardTypeEntity entity : dbValue) {
            if (entity.getRandomNumStr().equals(randomValue)) {
                randomValue = TyRandomUtil.getRandomNum(length);
                return getRandomValue(length, randomValue);
            }
        }

        return randomValue;
    }

    private void validate(CardTypeEntity entity) {
        String name = entity.getName();
        if (StringUtils.isEmpty(name)) {
            throw new ValidateException("卡密名称不能为空。");
        }


        String cardHead = entity.getCardHead();
        if (StringUtils.isEmpty(cardHead)) {
            throw new ValidateException("卡头不能为空。");
        }

        int randomFigure = entity.getRandomFigure();
        if (0 == randomFigure) {
            throw new ValidateException("随机位数不能为空。");
        }

        double denomination = entity.getDenomination();
        int type = entity.getCardType();
        if (1 == type) {
            if (0 == denomination) {
                throw new ValidateException("卡密面额不能为空。");
            }

            if (0 == entity.getUseInterval()) {
                throw new ValidateException("使用间隔不能为空。");
            }

            if (entity.getGoldenBean() == 0) {
                throw new ValidateException("获得金币不能为空。");
            }

            if (entity.getExperience() == 0) {
                throw new ValidateException("获得经验不能为空。");
            }

        } else if (2 == type) {
            if (0 == denomination) {
                throw new ValidateException("卡密面额不能为空。");
            }
        } else if (3 == type) {
            if (0 == entity.getGoldCoinEveryDay()) {
                throw new ValidateException("每日获得不能为空。");
            }
            if (0 == entity.getDrawDuration()) {
                throw new ValidateException("领取天数不能为空。");
            }

            if (StringUtils.isEmpty(entity.getIcon())) {
                throw new ValidateException("图标不能为空。");
            }
        } else {
            throw new ValidateException("不支持的卡类型。");
        }
    }
}
