package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.api.admin.CardMgntApi;
import com.taoyuan.gms.api.admin.CardPasswordApi;
import com.taoyuan.gms.core.adminmanage.service.ICardPasswordService;
import com.taoyuan.gms.model.entity.admin.CardPasswordEntity;
import com.taoyuan.gms.model.entity.admin.web.CDKeyEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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
    public CardPasswordEntity create(CardPasswordEntity cardPasswordEntity) {
        return null;
    }

    @Override
    public CardPasswordEntity update(CardPasswordEntity cardPasswordEntity) {
        return null;
    }

    @Override
    public TyResponse delete(Map<String, Object> map) {
        return null;
    }
}
