package com.taoyuan.gms.api.proxy;

import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.entity.admin.content.AnnouncementEntity;
import com.taoyuan.gms.model.entity.proxy.GoldenRechargeEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Api(value = "金币代充")
@RequestMapping("/proxy/goldenrecharge")
public interface GoldenRechargeApi {

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public TyResponse createGoldenRecharge(@RequestBody GoldenRechargeEntity entity);

    @RequestMapping(value = "/get5", method = RequestMethod.GET)
    public TyResponse get5GoldenRecharge(@RequestBody Map<String,Object> map);

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public TyResponse withdraw(@RequestBody GoldenRechargeEntity entity);
}
