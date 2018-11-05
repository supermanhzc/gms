package com.taoyuan.gms.api.adminmanage.prizemgnt.prize;

import com.taoyuan.gms.api.adminmanage.prizemgnt.classify.PrizeClassifyDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(value = "奖品管理服务")
@RequestMapping("/peizemgnt/prize")
public interface PrizeApi {
    /**
     * 查询所有奖品管理信息
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<PrizeClassifyDto> getPrizes();

    /**
     * 根据id查询奖品管理信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PrizeClassifyDto getPrize(long id);

    /**
     * 创建奖品管理
     * @param classifyName
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void createPrize(String classifyName);

    /**
     * 修改奖品管理
     * @param id
     * @param classifyName
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyPrize(String id, String classifyName);

    /**
     * 删除奖品管理
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePrize(long id);
}
