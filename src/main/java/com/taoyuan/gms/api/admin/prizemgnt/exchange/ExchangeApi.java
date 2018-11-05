package com.taoyuan.gms.api.admin.prizemgnt.exchange;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(value = "兑奖订单服务")
@RequestMapping("/peizemgnt/exchange")
public interface ExchangeApi {
    /**
     * 查询所有兑奖订单信息
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<ExchangeOrderDto> getExchangeOrders();

    /**
     * 根据id查询兑奖订单信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ExchangeOrderDto getExchangeOrder(long id);

    /**
     * 更新兑奖订单信息
     * @param id
     * @param status
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateStatus(long id,String status);
}
