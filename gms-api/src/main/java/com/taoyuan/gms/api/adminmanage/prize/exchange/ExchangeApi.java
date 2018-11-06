package com.taoyuan.gms.api.adminmanage.prize.exchange;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Api(value = "兑奖订单服务")
@RequestMapping("/peizemgnt/exchange")
public interface ExchangeApi {
    /**
     * 查询所有兑奖订单信息
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getExchangeOrders();

    /**
     * 根据id查询兑奖订单信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Map<String, Object> getExchangeOrder(@PathVariable("id") Long id);

    /**
     * 更新兑奖订单信息
     * @param id
     * @param status
     */
    @RequestMapping(value = "/{id}&{status}", method = RequestMethod.PUT)
    public void updateStatus(@PathVariable("id") Long id, @PathVariable("status") String status);
}
