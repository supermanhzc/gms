package com.taoyuan.gms.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.entity.admin.CardPasswordEntity;
import com.taoyuan.gms.model.entity.proxy.CardPassword;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

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
     * 回收卡密
     * @param map
     * @return
     */
    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public TyResponse withdraw(@RequestBody Map<String, Object> map);

    @RequestMapping(value = "/withdrawbatch", method = RequestMethod.POST)
    public TyResponse withdrawbatch(@RequestBody List<CardPassword> cardPasswordList);

    /**
     * 删除卡密
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete/{id}")
    public TyResponse delete(@PathVariable("id") String id);

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public List<CardPassword> getCardPasswordInfo(@RequestBody List<CardPassword> cardPasswordList);
}
