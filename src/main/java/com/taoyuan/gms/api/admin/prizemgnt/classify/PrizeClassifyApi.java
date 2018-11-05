package com.taoyuan.gms.api.admin.prizemgnt.classify;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(value = "奖品分类服务")
@RequestMapping("/peizemgnt/classify")
public interface PrizeClassifyApi {
    /**
     * 查询所有奖品分类信息
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<PrizeClassifyDto> getPrizeClassifies();

    /**
     * 根据id查询奖品分类信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PrizeClassifyDto getPrizeClassify(long id);

    /**
     * 创建奖品分类
     * @param classifyName
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void createPrizeClassfy(String classifyName);

    /**
     * 修改奖品分类
     * @param id
     * @param classifyName
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyPrizeClassfy(String id,String classifyName);

    /**
     * 删除奖品分类
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePrizeClassfy(long id);
}
