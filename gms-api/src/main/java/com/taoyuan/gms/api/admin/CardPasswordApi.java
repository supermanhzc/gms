package com.taoyuan.gms.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.entity.admin.CardPasswordEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Api(value = "卡密服务")
@RequestMapping("/cardpwdmgnt")
public interface CardPasswordApi {

    /**
     * 查询卡密信息
     * @param map
     * @return
     */
    @RequestMapping(value = "/retrieve", method = RequestMethod.POST)
    public IPage<Map<String, Object>> retrieve(@RequestBody Map<String,Object> map);

    /**
     * 批量创建卡密
     * @param map
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public List<CardPasswordEntity> create(@RequestBody Map<String, Object> map);

    /**
     * 修改卡密
     * @param map
     * @return
     */
    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public TyResponse withdraw(@RequestBody Map<String, Object> map);

    /**
     * 删除卡密
     * @param map
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public TyResponse delete(@RequestBody Map<String,Object> map);
}
