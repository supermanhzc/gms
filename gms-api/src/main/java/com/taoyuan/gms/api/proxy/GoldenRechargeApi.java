package com.taoyuan.gms.api.proxy;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.framework.common.entity.TyPageEntity;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.entity.admin.content.AnnouncementEntity;
import com.taoyuan.gms.model.entity.proxy.GoldenRechargeEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Api(value = "金币代充")
@RequestMapping("/proxy/goldenrecharge")
public interface GoldenRechargeApi {

    /**
     * 充值
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public TyResponse createGoldenRecharge(@RequestBody GoldenRechargeEntity entity);

    /**
     * 查询最近5笔记录
     *
     * @return
     */
    @RequestMapping(value = "/get5", method = RequestMethod.GET)
    public TyResponse get5GoldenRecharge();

    /**
     * 撤销
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public TyResponse withdraw(@RequestBody GoldenRechargeEntity entity);

    /**
     * 查询代充记录用，带分页
     * @param pageEntity
     * @return
     */
    @RequestMapping(value = "/records", method = RequestMethod.POST)
    public TyResponse retrieve(@RequestBody TyPageEntity pageEntity);
}
