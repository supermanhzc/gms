package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.common.constant.StatusCode;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.admin.CDKeyApi;
import com.taoyuan.gms.core.adminmanage.service.ICDkeyService;
import com.taoyuan.gms.model.entity.admin.web.CDKeyEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class CDKeyController extends BaseController implements CDKeyApi {

    @Autowired
    private ICDkeyService service;

    @Override
    public IPage<Map<String, Object>> retrieve(Map<String, Object> map) {
        Page page = getPage(map);
        return service.pageMaps(page, null);
    }

    @Override
    public CDKeyEntity create(CDKeyEntity cdKeyEntity) {
        service.saveOrUpdate(cdKeyEntity);
        return cdKeyEntity;
    }

    @Override
    public CDKeyEntity update(CDKeyEntity cdKeyEntity) {
        service.saveOrUpdate(cdKeyEntity);
        return cdKeyEntity;
    }

    @Override
    public TyResponse delete(Map<String, Object> map) {
        if (!map.containsKey("id")) {
            throw new ValidateException(StatusCode.FAIL.getCode(), "id不能为空。");
        }

        String id = (String) map.get("id");
        service.remove(new QueryWrapper<CDKeyEntity>().eq("id", id));
        return new TySuccessResponse(id);
    }
}
