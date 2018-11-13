package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.util.TyPageUtil;

import java.util.Map;

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
}
