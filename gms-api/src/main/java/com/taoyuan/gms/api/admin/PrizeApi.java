package com.taoyuan.gms.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.entity.admin.prize.PrizeEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Api(value = "奖品管理服务")
@RequestMapping("/prizemgnt/prize")
public interface PrizeApi {
    /**
     * 查询所有奖品管理信息
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public TyResponse getPrizes();

    /**
     * 根据id查询奖品信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TyResponse getPrize(@PathVariable("id") Long id);

    /**
     * 创建奖品
     *
     * @param classifyName
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public TyResponse createPrize(@RequestBody PrizeEntity classifyName);

    /**
     * 修改奖品
     *
     * @param prizeEntity
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public TyResponse modifyPrize(@RequestBody PrizeEntity prizeEntity);

    /**
     * 删除奖品管理
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public TyResponse deletePrize(@PathVariable("id") Long id);
}
