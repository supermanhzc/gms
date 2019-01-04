package com.taoyuan.gms.api.admin;

import com.taoyuan.framework.common.entity.TyPageEntity;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.dto.admin.excharge.ExchargeOrderRequest;
import com.taoyuan.gms.model.dto.admin.excharge.ExchargeOrderCreateRequest;
import com.taoyuan.gms.model.entity.admin.prize.ExchargeCardPwdEntity;
import com.taoyuan.gms.model.entity.admin.prize.ExchargeOrderEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "兑奖订单服务")
@RequestMapping("/prizemgnt/excharge")
public interface ExchargeApi {
    /**
     * 创建兑奖订单
     *
     * @return
     */
    @RequestMapping(value = "/order/create", method = RequestMethod.POST)
    public TyResponse create(@RequestBody ExchargeOrderCreateRequest order);

    /**
     * 根据id查询兑奖订单信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/order/retrieve/id={id}", method = RequestMethod.GET)
    public TyResponse getExchangeOrder(@PathVariable("id") Integer id);

    /**
     * 复杂条件查询兑奖订单信息
     *
     * @param request
     */
    @RequestMapping(value = "/order/retrieve", method = RequestMethod.POST)
    public TyResponse getExchangeOrders(@RequestBody ExchargeOrderRequest request);

    /**
     * 发货
     *
     * @param order
     * @return
     */
    @RequestMapping(value = "/order/sipping", method = RequestMethod.POST)
    public TyResponse sipping(@RequestBody ExchargeOrderEntity order);

    /**
     * 取消
     *
     * @param order
     * @return
     */
    @RequestMapping(value = "/order/cancel", method = RequestMethod.POST)
    public TyResponse cancel(@RequestBody ExchargeOrderEntity order);

    /**
     * 查询所有兑奖订单信息
     *
     * @return
     */
    @RequestMapping(value = "/cardpwd/create", method = RequestMethod.POST)
    public TyResponse create(@RequestBody ExchargeCardPwdEntity order);

    /**
     * 根据id查询兑奖订单信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/cardpwd/retrieve/id={id}", method = RequestMethod.GET)
    public TyResponse getExchangeCardPwd(@PathVariable("id") Long id);

    /**
     * 复杂条件查询兑奖订单信息
     *
     * @param page
     */
    @RequestMapping(value = "/cardpwd/retrieve", method = RequestMethod.POST)
    public TyResponse getExchangeCardPwds(@RequestBody TyPageEntity page);

    /**
     * 冻结
     *
     * @param order
     * @return
     */
    @RequestMapping(value = "/cardpwd/freeze", method = RequestMethod.POST)
    public TyResponse freeze(@RequestBody ExchargeCardPwdEntity order);

    /**
     * 解冻
     *
     * @param order
     * @return
     */
    @RequestMapping(value = "/cardpwd/unfreeze", method = RequestMethod.POST)
    public TyResponse unfreeze(@RequestBody ExchargeCardPwdEntity order);

    @RequestMapping(value = "/getExchargeSum/{id}", method = RequestMethod.POST)
    public TyResponse getExchargeSumByUserId(@PathVariable("id") Long id);
}
