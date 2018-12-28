package com.taoyuan.gms.core.proxymanage.controller;

import com.taoyuan.gms.core.adminmanage.controller.BaseGmsController;
import com.taoyuan.gms.core.proxymanage.service.IProxyOperService;
import com.taoyuan.gms.model.entity.proxy.ProxyOperEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@Slf4j
public class BaseGmsProxyController extends BaseGmsController {

    @Autowired
    private IProxyOperService operService;

    /**
     * 记录代理操作记录
     *
     * @param type
     * @param desp
     * @param money
     */
    public void recordOperation(int type, String desp, BigDecimal money) {
        Long proxyId = getCurrentUserId();
        String proxyName = getCurrentUserName();
        ProxyOperEntity operation = new ProxyOperEntity();
        BigDecimal moneyChanged = BigDecimal.ZERO;
        BigDecimal account = BigDecimal.ZERO;
        String description = "";
        //类型：1代充，2充值，3提现，4登录，5回收, 6互转, 7创建卡密
        operation.setType(type);
        if (1 == type) {
            //代充要扣除代理额度
            BigDecimal discountMoney =
                    money.multiply(BigDecimal.valueOf(getWebSetting().getProxyRechargeDiscount())).divide(BigDecimal.valueOf(100));
            //代充后要扣除代理额度
            moneyChanged = BigDecimal.ZERO.subtract(discountMoney);
            account = getBalance(proxyId);
            if (StringUtils.isEmpty(desp)) {
                description = "代充" + money;
            }
        } else if (2 == type) {
            //充值，增加代理额度
            moneyChanged = money;
            account = getBalance(proxyId);
            if (StringUtils.isEmpty(desp)) {
                description = "充值" + money;
            }
        } else if (3 == type) {
            //提现，代理余额扣除提现部分
            moneyChanged = BigDecimal.ZERO.subtract(money);
            account = getBalance(proxyId);
            if (StringUtils.isEmpty(desp)) {
                description = "提现" + money;
            }
        } else if (4 == type) {
            //登录，代理余额不变
            moneyChanged = BigDecimal.ZERO;
            account = getBalance(proxyId);
            if (StringUtils.isEmpty(desp)) {
                description = "登录";
            }
        } else if (5 == type) {
            //回收，账户变动为0，会员账号增加余额
            moneyChanged = BigDecimal.ZERO;
            account = getBalance(proxyId);
            if (StringUtils.isEmpty(desp)) {
                description = "回收卡密" + money;
            }
        } else if (6 == type) {
            //互转，减少代理余额
            moneyChanged = money;
            account = getBalance(proxyId);
            if (StringUtils.isEmpty(desp)) {
                description = "互转" + money;
            }
        } else if (7 == type) {
            moneyChanged = BigDecimal.ZERO.subtract(money);
            account = getBalance(proxyId);
            if (StringUtils.isEmpty(desp)) {
                description = "批量生成卡密" + money;
            }
        }
        operation.setMoneyChanged(moneyChanged);
        operation.setAccount(account);
        operation.setIp(getCurrentIp());
        operation.setDescription(desp);
        operation.setProxyId(proxyId);
        operation.setProxyName(proxyName);
        operation.setTime(new Date());
        log.info("operation is {}",operation);
        operService.save(operation);

        //同步更新代理余额
        updateBalance(proxyId, moneyChanged);
    }

    /**
     * 获取当前用户余额
     *
     * @return
     */
    public BigDecimal getCurrentUserBalance() {
        return getBalance(getCurrentUserId());
    }
}
