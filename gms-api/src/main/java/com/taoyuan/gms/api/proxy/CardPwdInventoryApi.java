package com.taoyuan.gms.api.proxy;

import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.dto.admin.CardPwdInventoryResquest;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 代理卡密库存
 */
@Api(value = "代理卡密库存")
@RequestMapping("/proxy/cardpwdinventory")
public interface CardPwdInventoryApi {

    /**
     * 查询代理卡密信息
     * @param resquest
     * @return
     */
    @RequestMapping(value = "/retrieve", method = RequestMethod.POST)
    public TyResponse retrieve(@RequestBody CardPwdInventoryResquest resquest);

    /**
     * 批量创建卡密
     * @param map
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public TyResponse create(@RequestBody Map<String, Object> map);

}
