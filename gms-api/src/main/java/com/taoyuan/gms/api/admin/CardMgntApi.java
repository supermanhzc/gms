package com.taoyuan.gms.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.entity.admin.web.CDKeyEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Api(value = "卡类管理服务")
@RequestMapping("/cardmgnt")
public interface CardMgntApi {

    /**
     * 查询卡信息
     * @param map
     * @return
     */
    @RequestMapping(value = "/retrieve", method = RequestMethod.POST)
    public IPage<Map<String, Object>> retrieve(@RequestBody Map<String,Object> map);

    /**
     * 创建卡
     * @param cdKeyEntity
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CDKeyEntity create(@RequestBody CDKeyEntity cdKeyEntity);

    /**
     * 修改卡
     * @param cdKeyEntity
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public CDKeyEntity update(@RequestBody CDKeyEntity cdKeyEntity);

    /**
     * 删除卡
     * @param map
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public TyResponse delete(@RequestBody Map<String,Object> map);
}
