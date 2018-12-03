package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taoyuan.framework.bs.controller.TyBaseController;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.gms.core.adminmanage.service.IUserService;
import com.taoyuan.gms.core.adminmanage.service.IWebSettingService;
import com.taoyuan.gms.model.entity.admin.account.UserEntity;
import com.taoyuan.gms.model.entity.admin.web.WebSettingEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
public abstract class BaseGmsController extends TyBaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IWebSettingService webSettingService;

    /**
     * 查询用户余额
     *
     * @param id
     * @return
     */
    public BigDecimal getBalance(Long id) {
        UserEntity account = getUserById(id);
        if (null == account) {
            return BigDecimal.ZERO;
        }

        return account.getBalance();
    }


    /**
     * 更新用户余额
     *
     * @param id
     * @param money
     */
    public void updateBalance(Long id, BigDecimal money) {
        UserEntity user = getUserById(id);
        if (null == user) {
            user = new UserEntity();
            user.setId(id);
            user.setNickName("");
            user.setBalance(BigDecimal.ZERO);
            userService.save(user);
        } else {
            user.setBalance(user.getBalance().add(money));
            userService.updateById(user);
        }
    }

    /**
     * 查询当前操作用户ID
     *
     * @return
     */
    public Long getCurrentUserId() {
        log.info("current account id:{}", TySession.getCurrentUser().getUserId());
        return TySession.getCurrentUser().getUserId();
    }

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    public UserEntity getUserById(Long id) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<UserEntity>();
        wrapper.lambda().eq(UserEntity::getId, id);
        UserEntity user = userService.getOne(wrapper);
        log.info("User info:{}", user);
        return user;
    }

    public WebSettingEntity getWebSetting() {
        return webSettingService.getOne(null);
    }
}
