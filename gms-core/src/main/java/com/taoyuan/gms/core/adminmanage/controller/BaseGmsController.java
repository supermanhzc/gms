package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.bs.controller.TyBaseController;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.framework.common.util.TyPageUtil;
import com.taoyuan.gms.core.adminmanage.service.IUserService;
import com.taoyuan.gms.model.entity.admin.account.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
@RestController
public abstract class BaseGmsController extends TyBaseController {

    @Autowired
    private IUserService userService;

    //TODO    此处需要调用接口查询用户余额

    /**
     * 查询用户余额
     *
     * @param id
     * @return
     */
    public BigDecimal getBalance(Long id) {
        //TODO 此处逻辑先屏蔽，后面等功能提供后放开
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<UserEntity>();
        wrapper.lambda().eq(UserEntity::getId, id);
        UserEntity account = userService.getOne(wrapper);
        return BigDecimal.valueOf(1000000);
    }

    //TODO 此处需要调用接口更新用户余额

    /**
     * 更新用户余额
     *
     * @param id
     * @param money
     */
    public void updateBalance(Long id, BigDecimal money) {
        BigDecimal balance = getBalance(id).subtract(money);

        //TODO 此处接口实现后放开
//        QueryWrapper<UserEntity> wrapper = new QueryWrapper<UserEntity>();
//        wrapper.lambda().eq(UserEntity::getId, id);
//        UserEntity account = userService.getOne(wrapper);
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

    public String getCurrentUserName() {
        return TySession.getCurrentUser().getName();
    }

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    public UserEntity getById(Long id) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<UserEntity>();
        wrapper.lambda().eq(UserEntity::getId, id);
        UserEntity user = userService.getOne(wrapper);
        log.info("User info:{}", user);
        return user;
    }
}
