package com.taoyuan.gms.core.adminmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.dto.admin.UserBalanceChangeDto;
import com.taoyuan.gms.model.entity.admin.account.UserBalanceChangeEntity;

public interface IUserBalanceChangeService extends IService<UserBalanceChangeEntity> {
    TyResponse changeBalance(UserBalanceChangeDto userBalanceChangeDto);
}
