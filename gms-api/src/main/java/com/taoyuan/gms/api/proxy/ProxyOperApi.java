package com.taoyuan.gms.api.proxy;

import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.dto.BaseKeywordPageRequest;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "代理操作日志")
@RequestMapping("/proxy/oper")
public interface ProxyOperApi {

    @RequestMapping(value = "/retrieve", method = RequestMethod.POST)
    public TyResponse retrieve(@RequestBody BaseKeywordPageRequest request);
}
