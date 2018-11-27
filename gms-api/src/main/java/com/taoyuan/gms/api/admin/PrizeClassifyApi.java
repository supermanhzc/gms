package com.taoyuan.gms.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.entity.admin.prize.PrizeClassifyEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Api(value = "奖品分类服务")
@RequestMapping("/prizemgnt/classify")
public interface PrizeClassifyApi {
    /**
     * 查询所有奖品分类信息
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<PrizeClassifyEntity> getPrizeClassifies();

    /**
     * 根据id查询奖品分类信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PrizeClassifyEntity getPrizeClassify(@PathVariable("id") Long id);

    /**
     * 创建奖品分类
     * @param classify
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public PrizeClassifyEntity createPrizeClassfy(@RequestBody PrizeClassifyEntity classify);

    /**
     * 修改奖品分类
     * @param id
     * @param classifyName
     */
    @RequestMapping(value = "/id={id}&classifyName={classifyName}", method = RequestMethod.PUT)
    public PrizeClassifyEntity modifyPrizeClassfy(@PathVariable("id") Long id, @PathVariable("classifyName") String classifyName);

    /**
     * 删除奖品分类
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public TyResponse deletePrizeClassfy(@PathVariable("id") Long id);
}
