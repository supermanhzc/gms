package com.taoyuan.gms.core.adminmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taoyuan.gms.model.dto.admin.account.UpdateAccountBalanceRequest;
import com.taoyuan.gms.model.entity.admin.account.UserBalanceChangeEntity;

public interface IUserBalanceChangeService extends IService<UserBalanceChangeEntity> {
    boolean changeBalance(UpdateAccountBalanceRequest updateAccountBalanceRequest);
}
