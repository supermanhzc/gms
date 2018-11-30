package com.taoyuan.gms.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.gms.model.entity.admin.prize.ExchargeCardPwdEntity;
import com.taoyuan.gms.model.entity.admin.prize.ExchargeOrderEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.nio.cs.ext.ExtendedCharsets;

import java.util.Map;

@Api(value = "兑奖订单服务")
@RequestMapping("/prizemgnt/excharge")
public interface ExchargeApi {
    /**
     * 查询所有兑奖订单信息
     * @return
     */
    @RequestMapping(value = "/order/create", method = RequestMethod.POST)
    public ExchargeOrderEntity create(@RequestBody ExchargeOrderEntity order);

    /**
     * 根据id查询兑奖订单信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/order/retrieve/id={id}", method = RequestMethod.GET)
    public ExchargeOrderEntity getExchangeOrder(@PathVariable("id") Integer id);

    /**
     * 复杂条件查询兑奖订单信息
     * @param map
     */
    @RequestMapping(value = "/order/retrieve", method = RequestMethod.POST)
    public IPage<Map<String, Object>> getExchangeOrders(@RequestBody Map<String,Object> map);

    /**
     * 发货
     * @param order
     * @return
     */
    @RequestMapping(value = "/order/sipping", method = RequestMethod.POST)
    public ExchargeOrderEntity sipping(@RequestBody ExchargeOrderEntity order);

    /**
     * 取消
     * @param order
     * @return
     */
    @RequestMapping(value = "/order/cancel", method = RequestMethod.POST)
    public ExchargeOrderEntity cancel(@RequestBody ExchargeOrderEntity order);

    /**
     * 查询所有兑奖订单信息
     * @return
     */
    @RequestMapping(value = "/cardpwd/create", method = RequestMethod.POST)
    public ExchargeCardPwdEntity create(@RequestBody ExchargeCardPwdEntity order);

    /**
     * 根据id查询兑奖订单信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/cardpwd/retrieve/id={id}", method = RequestMethod.GET)
    public ExchargeCardPwdEntity getExchangeCardPwd(@PathVariable("id") Long id);

    /**
     * 复杂条件查询兑奖订单信息
     * @param map
     */
    @RequestMapping(value = "/cardpwd/retrieve", method = RequestMethod.POST)
    public IPage<Map<String, Object>> getExchangeCardPwds(@RequestBody Map<String,Object> map);

    /**
     * 冻结
     * @param order
     * @return
     */
    @RequestMapping(value = "/cardpwd/freeze", method = RequestMethod.POST)
    public ExchargeCardPwdEntity freeze(@RequestBody ExchargeCardPwdEntity order);

    /**
     * 解冻
     * @param order
     * @return
     */
    @RequestMapping(value = "/cardpwd/unfreeze", method = RequestMethod.POST)
    public ExchargeCardPwdEntity unfreeze(@RequestBody ExchargeCardPwdEntity order);
}
