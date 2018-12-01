package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.framework.common.entity.TyUserRolePermission;
import com.taoyuan.framework.common.exception.TyBusinessException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.admin.UserBalanceChangeApi;
import com.taoyuan.gms.core.adminmanage.service.IUserBalanceChangeService;
import com.taoyuan.gms.model.dto.admin.UserBalanceChangeDto;
import com.taoyuan.gms.model.entity.admin.account.UserBalanceChangeEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
public class UserBalanceChangeController implements UserBalanceChangeApi{
    @Autowired
    private IUserBalanceChangeService uerBalanceChangeService;

    @Override
    public IPage<Map<String, Object>> getAllBalanceChangeHistory(Integer pageIndex, Integer pageSize) {
        return null;
    }

    @Override
    public TyResponse changeBalance(UserBalanceChangeDto userBalanceChangeDto) {
        return uerBalanceChangeService.changeBalance(userBalanceChangeDto);
    }
}
