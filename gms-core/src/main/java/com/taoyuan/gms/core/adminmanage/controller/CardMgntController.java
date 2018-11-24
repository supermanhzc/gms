package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.common.constant.StatusCode;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.framework.common.util.TyRandomUtil;
import com.taoyuan.gms.api.admin.CardMgntApi;
import com.taoyuan.gms.core.adminmanage.service.ICardMgntService;
import com.taoyuan.gms.model.entity.admin.web.CDKeyEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class CardMgntController extends BaseController implements CardMgntApi {

    @Autowired
    private ICardMgntService service;

    @Override
    public IPage<Map<String, Object>> retrieve(Map<String, Object> map) {
        Page page = getPage(map);

        QueryWrapper<CDKeyEntity> wrapper = new QueryWrapper<CDKeyEntity>();
        if (map.containsKey("cardType")) {
            wrapper.lambda().eq(CDKeyEntity::getCardType, map.get("cardType"));
        }
        if (map.containsKey("cardId")) {
            wrapper.lambda().eq(CDKeyEntity::getCardId, map.get("cardId"));
        }
        return service.pageMaps(page, wrapper);
    }

    @Override
    public CDKeyEntity create(CDKeyEntity cdKeyEntity) {
        if (StringUtils.isEmpty(cdKeyEntity.getName())) {
            throw new ValidateException("名称不能为空。");
        }

        int randomFigure = cdKeyEntity.getRandomFigure();
        String randomNumStr = getRandomValue(randomFigure, TyRandomUtil.getRandomNum(randomFigure));
        cdKeyEntity.setRandomNumStr(randomNumStr);
        cdKeyEntity.setCardId(cdKeyEntity.getCardHead() + randomNumStr);
        cdKeyEntity.setCreateUser(TySession.getCurrentUser().getUserId());
        service.saveOrUpdate(cdKeyEntity);
        return cdKeyEntity;
    }

    @Override
    public CDKeyEntity update(CDKeyEntity cdKeyEntity) {
        if (StringUtils.isEmpty(cdKeyEntity.getName())) {
            throw new ValidateException("名称不能为空。");
        }

        int randomFigure = cdKeyEntity.getRandomFigure();
        String randomNumStr = getRandomValue(randomFigure, TyRandomUtil.getRandomNum(randomFigure));
        cdKeyEntity.setRandomNumStr(randomNumStr);
        cdKeyEntity.setCardId(cdKeyEntity.getCardHead() + randomNumStr);
        cdKeyEntity.setCreateUser(TySession.getCurrentUser().getUserId());
        service.saveOrUpdate(cdKeyEntity);
        return cdKeyEntity;
    }

    @Override
    public TyResponse delete(Map<String, Object> map) {
        if (!map.containsKey("cardId")) {
            throw new ValidateException(StatusCode.FAIL.getCode(), "id不能为空。");
        }

        String id = (String) map.get("cardId");
        service.remove(new QueryWrapper<CDKeyEntity>().eq("id", id));
        return new TySuccessResponse(id);
    }

    private String getRandomValue(int length, String randomValue) {
        QueryWrapper<CDKeyEntity> wrapper = new QueryWrapper<CDKeyEntity>();
        wrapper.lambda().eq(CDKeyEntity::getRandomFigure, length);
        List<CDKeyEntity> dbValue = service.list(wrapper);
        for (CDKeyEntity entity : dbValue) {
            if (entity.getRandomNumStr().equals(randomValue)) {
                randomValue = TyRandomUtil.getRandomNum(length);
                return getRandomValue(length, randomValue);
            }
        }

        return randomValue;
    }
}
