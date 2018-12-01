package com.taoyuan.gms.core.adminmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.framework.common.entity.TyUserRolePermission;
import com.taoyuan.framework.common.exception.TyBusinessException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.core.adminmanage.dao.UserBalanceChangeMapper;
import com.taoyuan.gms.core.adminmanage.service.IUserBalanceChangeService;
import com.taoyuan.gms.model.dto.admin.UserBalanceChangeDto;
import com.taoyuan.gms.model.entity.admin.account.UserBalanceChangeEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserBalanceChangeServiceImpl extends ServiceImpl<UserBalanceChangeMapper, UserBalanceChangeEntity> implements IUserBalanceChangeService {
    @Override
    public TyResponse changeBalance(UserBalanceChangeDto userBalanceChangeDto) {
        UserBalanceChangeEntity userBalanceChangeEntity = new UserBalanceChangeEntity();
        BeanUtils.copyProperties(userBalanceChangeDto, userBalanceChangeEntity);

        TyUserRolePermission currentUser = TySession.getCurrentUser();
        userBalanceChangeEntity.setUpdateUser(currentUser.getUserId());
        if (this.save(userBalanceChangeEntity)){
            return new TySuccessResponse(userBalanceChangeEntity);
        }
        throw new TyBusinessException("account balance change failed.");
    }
}
