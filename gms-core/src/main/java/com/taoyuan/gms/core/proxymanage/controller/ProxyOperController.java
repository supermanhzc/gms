package com.taoyuan.gms.core.proxymanage.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.bs.controller.TyBaseController;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.proxy.ProxyOperApi;
import com.taoyuan.gms.core.proxymanage.service.IProxyOperService;
import com.taoyuan.gms.model.dto.BaseKeywordPageRequest;
import com.taoyuan.gms.model.entity.proxy.ProxyOperEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyOperController extends TyBaseController implements ProxyOperApi {

    @Autowired
    private IProxyOperService proxyOperService;

    @Override
    public TyResponse retrieve(@RequestBody BaseKeywordPageRequest request) {
        Page page = getPage(request);

        QueryWrapper<ProxyOperEntity> queryWrapper = new QueryWrapper<ProxyOperEntity>();
        queryWrapper.lambda().eq(ProxyOperEntity::getProxyId, getCurrentUserId());
        String keyword = request.getKeyword();
        if (!StringUtils.isEmpty(keyword)) {
            int type = Integer.valueOf(keyword);
            queryWrapper.lambda().eq(ProxyOperEntity::getType, type);
        }
        return new TySuccessResponse(proxyOperService.pageMaps(page, queryWrapper));
    }
}
