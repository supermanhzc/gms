package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.admin.PrizeClassifyApi;
import com.taoyuan.gms.core.adminmanage.service.IPrizeClassifyService;
import com.taoyuan.gms.model.entity.admin.prize.PrizeClassifyEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class PrizeClassifyController extends BaseGmsController implements PrizeClassifyApi {

    @Autowired
    private IPrizeClassifyService service;

    @Override
    public TyResponse getPrizeClassifies() {
        return new TySuccessResponse(service.list(null));
    }

    @Override
    public TyResponse getPrizeClassify(Long id) {
        return new TySuccessResponse(service.getById(id));
    }

    @Override
    public TyResponse createPrizeClassfy(@RequestBody PrizeClassifyEntity classify) {
        if (StringUtils.isEmpty(classify.getName())) {
            throw new ValidateException("类目名称不能为空。");
        }

        PrizeClassifyEntity entity = new PrizeClassifyEntity();
        entity.setName(classify.getName());
        service.save(entity);
        return new TySuccessResponse(entity);
    }

    @Override
    public TyResponse modifyPrizeClassfy(Long id, String classifyName) {
        if (null == id) {
            throw new ValidateException("id不能为空。");
        }

        if (StringUtils.isEmpty(classifyName)) {
            throw new ValidateException("类目名称不能为空。");
        }

        PrizeClassifyEntity entity = service.getById(id);
        entity.setName(classifyName);
        service.saveOrUpdate(entity);
        return new TySuccessResponse(entity);
    }

    @Override
    public TyResponse deletePrizeClassfy(Long id) {
        if (null == id) {
            throw new ValidateException("id不能为空。");
        }

        service.remove(new QueryWrapper<PrizeClassifyEntity>().lambda().eq(PrizeClassifyEntity::getId, id));
        return new TySuccessResponse(id);
    }
}
