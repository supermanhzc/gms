package com.taoyuan.gms.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.entity.admin.web.CardTypeEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "卡类管理服务")
@RequestMapping("/cardmgnt")
public interface CardTypeApi {

    /**
     * 根据id查询卡类信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/retrieve/id={id}", method = RequestMethod.GET)
    public CardTypeEntity retrieve(@PathVariable Long id);

    /**
     * 查询卡信息
     * @param map
     * @return
     */
    @RequestMapping(value = "/retrieve", method = RequestMethod.POST)
    public IPage<Map<String, Object>> retrieve(@RequestBody Map<String,Object> map);

    /**
     * 创建卡
     * @param cardTypeEntity
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CardTypeEntity create(@RequestBody CardTypeEntity cardTypeEntity);

    /**
     * 修改卡
     * @param cardTypeEntity
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public CardTypeEntity update(@RequestBody CardTypeEntity cardTypeEntity);

    /**
     * 删除卡
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public TyResponse delete(@PathVariable("id") String id);
}
