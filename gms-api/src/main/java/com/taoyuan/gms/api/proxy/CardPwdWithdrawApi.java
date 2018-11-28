package com.taoyuan.gms.api.proxy;

import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.entity.proxy.CardPassword;
import com.taoyuan.gms.model.entity.proxy.GoldenRechargeEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(value = "卡密回收")
@RequestMapping("/proxy/cardpwd")
public interface CardPwdWithdrawApi {

    /**
     * 最近10笔记录
     * @return
     */
    @RequestMapping(value = "/getLatest10", method = RequestMethod.GET)
    TyResponse getLatest10();

    /**
     * 回收
     * @param cpList
     * @return
     */
    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public TyResponse withdraw(@RequestBody List<CardPassword> cpList);
}
