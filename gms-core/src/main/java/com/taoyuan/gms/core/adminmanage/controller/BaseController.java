package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.util.TyPageUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
public abstract class BaseController {

    public BaseController() {

    }

    public Page getPage(Map map) {
        return TyPageUtil.getPage(map);
    }

    public Page getPage(Integer index,Integer size) {
        validatePageParams(index,size);
        return new Page(index,size);
    }

    public void throwValidateException(Integer code, String msg) {
        throw new ValidateException(code, msg, null);
    }

    public void throwValidateException(Integer code, String msg, String param) {
        throw new ValidateException(code, msg, param);
    }

    public void validatePageParams(Integer pageIndex, Integer pageSize) {
        validateParam(pageIndex, 10000001, "pageIndex");
        validateParam(pageIndex, 10000002, "pageSize");
    }

    private void validateParam(Object obj, Integer code, String name) {
        if (null == obj) {
            throw new ValidateException(code, "参数不能为空。", name);
        }
    }

    public void validateNullString(String value,String tip){
        if(StringUtils.isEmpty(value)){
            throw  new ValidateException(tip+"不能为空");
        }
    }

    public void validateDoubleZero(double value,String tip){
        if(0==value){
            throw  new ValidateException(tip+"不能为空");
        }
    }

    public void validateIntZero(int value,String tip){
        if(0==value){
            throw  new ValidateException(tip+"不能为空");
        }
    }

    //TODO    此处需要调用接口查询用户余额
    public BigDecimal getBalance(Long id){
        return BigDecimal.valueOf(20000);
    }

    //TODO 此处需要调用接口更新用户余额
    public void updateBalance(Long id,BigDecimal money){
        BigDecimal balance = getBalance(id).subtract(money);
    }
}
