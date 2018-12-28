package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.framework.aaa.service.TyUserService;
import com.taoyuan.framework.bs.aspect.OperControllerLog;
import com.taoyuan.framework.common.entity.TyUser;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.framework.common.util.TyRandomUtil;
import com.taoyuan.gms.api.admin.CardPasswordApi;
import com.taoyuan.gms.common.util.StringUtil;
import com.taoyuan.gms.core.adminmanage.dao.CardPasswordMapper;
import com.taoyuan.gms.core.adminmanage.service.ICardPasswordService;
import com.taoyuan.gms.model.dto.admin.card.CardPasswordRequest;
import com.taoyuan.gms.model.entity.admin.card.CardPasswordEntity;
import com.taoyuan.gms.model.entity.proxy.CardPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class CardPasswordController extends BaseGmsController
        implements CardPasswordApi {

    @Autowired
    private ICardPasswordService service;

    @Autowired
    private CardPasswordMapper mapper;

    @Autowired
    private TyUserService userService;

    @Override
    @OperControllerLog(module = "卡密管理", type = "条件查询卡密")
    public TyResponse retrieve(@RequestBody CardPasswordRequest request) {
        Page page = getPage(request);

        QueryWrapper<CardPasswordEntity> wrapper =
                new QueryWrapper<CardPasswordEntity>();
        if (!StringUtils.isEmpty(request.getKeyword())) {
            String keyword = request.getKeyword();
            wrapper.lambda()
                    .eq(CardPasswordEntity::getCardId, keyword)
                    .or()
                    .eq(CardPasswordEntity::getCardPassword, keyword)
                    .or()
                    .eq(CardPasswordEntity::getRechargeId, keyword);
        }

        if (request.getCardType() != 0) {
            wrapper.lambda()
                    .eq(CardPasswordEntity::getCardType, request.getCardType());
        }

        if (request.getStatus() != 0) {
            wrapper.lambda()
                    .eq(CardPasswordEntity::getStatus, request.getStatus());
        }

        if (!StringUtils.isEmpty(request.getOwner())) {
            wrapper.lambda()
                    .eq(CardPasswordEntity::getOwner, request.getOwner());
        }
        wrapper.lambda().orderByDesc(CardPasswordEntity::getCreateTime);
        return new TySuccessResponse(mapper.selectPage(page, wrapper));
    }

    @Override
    @OperControllerLog(module = "卡密管理", type = "创建卡密")
    public TyResponse create(@RequestBody Map<String, Object> map) {
        if (!map.containsKey("cardType")) {
            throw new ValidateException("卡类型不能为空。");
        }
        if (!map.containsKey("number")) {
            throw new ValidateException("数量不能为空。");
        }

        int cardType = (int) map.get("cardType");
        String cardHead = getCardHead(cardType);
        int number = (int) map.get("number");

        List<CardPasswordEntity> entityList =
                new ArrayList<CardPasswordEntity>();
        for (int i = 0; i < number; i++) {
            // 默认生成随机8位id和密码
            String id = TyRandomUtil.getRandomNum(8);
            log.info("generate id is {}", id);
            String pwd = TyRandomUtil.getRandomStr(8);
            log.info("generate password is {}", pwd);
            CardPasswordEntity entity = new CardPasswordEntity();
            entity.setCardType(cardType);
            entity.setCardId(cardHead + id);
            entity.setStatus(1);
            entity.setCardPassword(pwd);
            entity.setCreateTime(new Date());
            entity.setCreateUser(TySession.getCurrentUser().getUserId());
            entity.setOwner(TySession.getCurrentUser().getName());
            entity.setStartTime(new Date());
            entityList.add(entity);
        }
        service.saveBatch(entityList);

        // 更新库存

        return new TySuccessResponse(entityList);
    }

    @Override
    @OperControllerLog(module = "卡密管理", type = "撤销卡密")
    public TyResponse withdraw(@RequestBody CardPassword cardPassword) {
        log.info("input:{}", cardPassword);
        String cardId = cardPassword.getCardId();
        if (StringUtils.isEmpty(cardId)) {
            throw new ValidateException("卡号不能为空。");
        }

        String pwd = cardPassword.getPassword();
        if (StringUtils.isEmpty(pwd)) {
            throw new ValidateException("密码不能为空。");
        }

        CardPasswordEntity entity = service.getByCardId(cardId);
        log.info("withdraw card password:{}", entity);
        // 设置状态为已回收
        entity.setStatus(2);
        entity.setEndTime(new Date());
        service.saveOrUpdate(entity);

        // 回收后给用户增加对应金额
        if (null != entity.getRechargeId()) {
            updateBalance(Long.valueOf(entity.getRechargeId()), entity.getMoney());
        }
        return new TySuccessResponse(cardId);
    }

    @Override
    @OperControllerLog(module = "卡密管理", type = "批量撤销卡密")
    public TyResponse withdrawbatch(
            @RequestBody List<CardPassword> cardPasswordList) {
        if (CollectionUtils.isEmpty(cardPasswordList)) {
            return new TySuccessResponse(null);
        }

        List<CardPasswordEntity> dbValueList =
                new ArrayList<CardPasswordEntity>();
        for (CardPassword cp : cardPasswordList) {
            String cardId = cp.getCardId();
            if (StringUtils.isEmpty(cardId)) {
                throw new ValidateException("待回收卡密卡号不能为空。");
            }
            String pwd = cp.getPassword();
            if (StringUtils.isEmpty(pwd)) {
                throw new ValidateException("待回收卡密密码不能为空。");
            }

            CardPasswordEntity dbValue = service.getByCardIdAndPwd(cardId, pwd);
            if (null != dbValue) {
                // 设置状态为已回收
                dbValue.setStatus(2);
                dbValue.setEndTime(new Date());
                dbValueList.add(dbValue);
            }

        }

        log.info("card password list:{}", dbValueList);
        service.saveOrUpdateBatch(dbValueList);

        // 回收后给用户增加对应金额
        for (CardPasswordEntity cp : dbValueList) {
            updateBalance(Long.valueOf(cp.getRechargeId()), cp.getMoney());
        }

        return new TySuccessResponse(dbValueList);
    }

    @Override
    @OperControllerLog(module = "卡密管理", type = "批量取消卡密")
    public TyResponse cancelbatch(
            @RequestBody List<CardPassword> cardPasswordList) {
        if (CollectionUtils.isEmpty(cardPasswordList)) {
            return new TySuccessResponse(null);
        }

        List<CardPasswordEntity> dbValueList =
                new ArrayList<CardPasswordEntity>();
        for (CardPassword cp : cardPasswordList) {
            String cardId = cp.getCardId();
            if (StringUtils.isEmpty(cardId)) {
                throw new ValidateException("待撤销卡密卡号不能为空。");
            }

            String pwd = cp.getPassword();
            if (StringUtils.isEmpty(pwd)) {
                throw new ValidateException("待撤销卡密密码不能为空。");
            }

            CardPasswordEntity dbValue = service.getByCardIdAndPwd(cardId, pwd);
            if (null != dbValue) {
                // 设置状态为已撤销
                dbValue.setStatus(2);
                dbValue.setEndTime(new Date());
                dbValueList.add(dbValue);
            }

        }

        log.info("card list is {}", dbValueList);
        service.saveOrUpdateBatch(dbValueList);

        Long proxyId = getCurrentUserId();
        BigDecimal cancelMoney = BigDecimal.ZERO;
        // 计算需要撤销的总金额
        for (CardPasswordEntity cp : dbValueList) {
            cancelMoney.add(cp.getMoney());
        }
        // 更新代理余额
        updateBalance(proxyId, cancelMoney);

        return new TySuccessResponse(dbValueList);
    }

    @Override
    @OperControllerLog(module = "卡密管理", type = "取消卡密")
    public TyResponse cancel(@RequestBody CardPassword cardPassword) {
        String cardId = cardPassword.getCardId();
        if (StringUtils.isEmpty(cardId)) {
            throw new ValidateException("卡号不能为空。");
        }

        String pwd = cardPassword.getPassword();
        if (StringUtils.isEmpty(pwd)) {
            throw new ValidateException("密码不能为空。");
        }

        CardPasswordEntity entity = service.getByCardId(cardId);
        log.info("cancel object:{}", entity);
        // 设置状态为已撤销
        entity.setStatus(3);
        entity.setEndTime(new Date());
        service.saveOrUpdate(entity);

        // 更新用户余额
        updateBalance(getCurrentUserId(), entity.getMoney());
        return new TySuccessResponse(cardId);
    }

    @Override
    @OperControllerLog(module = "卡密管理", type = "删除卡密")
    public TyResponse delete(@PathVariable String id) {
        if (null == id) {
            throw new ValidateException("卡号不能为空。");
        }
        log.info("card password id:{}", id);
        CardPasswordEntity entity = service.getByCardId(id);
        if (null == entity) {
            throw new ValidateException("对象不存在。");
        }

        if (!StringUtils.isEmpty(entity.getRechargeId())) {
            throw new ValidateException("不能删除已经兑换的卡。");
        }

        // 只有管理员才能删除，代理和会员不能删除
        service
                .remove(new QueryWrapper<CardPasswordEntity>().eq("card_id", id));
        return new TySuccessResponse(id);
    }

    @Override
    @OperControllerLog(module = "卡密管理", type = "查询卡密状态")
    public TyResponse getCardPasswordInfo(
            @RequestBody List<CardPassword> cardPasswordList) {
        log.info("input is {}", cardPasswordList);
        if (CollectionUtils.isEmpty(cardPasswordList)) {
            return new TySuccessResponse(cardPasswordList);
        }

        List<CardPassword> rsltList = new ArrayList<CardPassword>();
        List<String> cardIdList = new ArrayList<String>();
        for (CardPassword cp : cardPasswordList) {
            String cardId = cp.getCardId();
            if (StringUtils.isEmpty(cardId)) {
                throw new ValidateException("卡密卡号不能为空。");
            }

            CardPasswordEntity dbValue = service.getByCardId(cardId);
            if (null == dbValue) {
                cp.setInfo("卡号或密码不正确");
                rsltList.add(cp);
                continue;
            }

            // 已回收,已充值
            if (dbValue.getStatus() == 2) {
                cp.setInfo("卡密已回收");
                rsltList.add(cp);
                continue;
            }

            // 已撤销
            if (dbValue.getStatus() == 3) {
                cp.setInfo("卡密已撤销");
                rsltList.add(cp);
                continue;
            }

            String pwd = cp.getPassword();
            if (StringUtils.isEmpty(pwd.trim())) {
                cp.setInfo("卡号或密码不正确");
                rsltList.add(cp);
                continue;
            }

            if (pwd.equals(dbValue.getCardPassword())) {
                if (dbValue.getRechargeId() != null) {
                    String rechargeId = dbValue.getRechargeId();
                    TyUser user =
                            userService.getUserById(Long.valueOf(rechargeId));
                    log.info("account info:{}", user);
                    cp.setRechargeId(rechargeId);
                    cp.setName(user.getName());
                    // TODO
                    // 待增加获取用户昵称和QQ
                    cp.setNickName(user.getName());
                    cp.setQq(user.getName());
                }
            } else {
                cp.setInfo("卡号或密码不正确");
            }

            if (cardIdList.contains(cardId)) {
                continue;
            }
            cardIdList.add(cardId);

            rsltList.add(cp);
        }
        return new TySuccessResponse(rsltList);
    }

    @Override
    @OperControllerLog(module = "卡密管理", type = "查询卡密")
    public TyResponse query(@RequestBody CardPasswordRequest request) {
        Page page = getPage(request);

        QueryWrapper<CardPasswordEntity> queryWrapper =
                new QueryWrapper<CardPasswordEntity>();
        if (!StringUtils.isEmpty(request.getCreateUser())) {
            Long owner = request.getCreateUser();
            queryWrapper.lambda().eq(CardPasswordEntity::getCreateUser, owner);
        }

        String keyword = request.getKeyword();
        if (!StringUtils.isEmpty(keyword)) {
            if (StringUtil.isNumber(keyword)) {
                queryWrapper.and(wrapper -> wrapper
                        .eq("card_id", keyword)
                        .or()
                        .eq("card_password", keyword)
                        .or()
                        .eq("recharge_id", Long.valueOf(keyword)));
            } else {
                queryWrapper.and(wrapper -> wrapper
                        .eq("card_id", keyword)
                        .or()
                        .eq("card_password", keyword));
            }
        }

        if (request.getCardType() != 0) {
            int cardType = (int) request.getCardType();
            queryWrapper.lambda().eq(CardPasswordEntity::getCardType, cardType);
        }

        queryWrapper.lambda().orderByDesc(CardPasswordEntity::getCreateTime);
        return new TySuccessResponse(mapper.selectPage(page, queryWrapper));
    }

    private String getCardHead(int cardType) {
        String cardHead = null;
        switch (cardType) {
            case 1:
                cardHead = "10y";
                break;
            case 2:
                cardHead = "20y";
                break;
            case 3:
                cardHead = "30y";
                break;
            case 4:
                cardHead = "redzuan";
                break;
            default:
                throw new ValidateException("不支持的卡类型。");
        }
        return cardHead;
    }
}
