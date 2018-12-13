package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taoyuan.framework.bs.aspect.OperControllerLog;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.admin.PrizeClassifyApi;
import com.taoyuan.gms.core.adminmanage.service.IPrizeClassifyService;
import com.taoyuan.gms.model.entity.admin.prize.PrizeClassifyEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class PrizeClassifyController extends BaseGmsController implements PrizeClassifyApi {

    @Autowired
    private IPrizeClassifyService service;

    @Override
    @OperControllerLog(module = "奖品分类管理", type = "查询所有奖品分类")
    public TyResponse getPrizeClassifies() {
        return new TySuccessResponse(service.list(null));
    }

    @Override
    @OperControllerLog(module = "奖品分类管理", type = "分页查询奖品分类")
    public TyResponse getPrizeClassify(@PathVariable Long id) {
        return new TySuccessResponse(service.getById(id));
    }

    @Override
    @OperControllerLog(module = "奖品分类管理", type = "创建奖品分类")
    public TyResponse createPrizeClassfy(@RequestBody PrizeClassifyEntity classify) {
        if (StringUtils.isEmpty(classify.getName())) {
            throw new ValidateException("类目名称不能为空。");
        }

        PrizeClassifyEntity entity = new PrizeClassifyEntity();
        entity.setName(classify.getName());
        service.save(entity);
        return new TySuccessResponse(entity);
    }

    @Override
    @OperControllerLog(module = "奖品分类管理", type = "修改奖品分类")
    public TyResponse modifyPrizeClassfy(@PathVariable Long id, @PathVariable String classifyName) {
        if (null == id) {
            throw new ValidateException("id不能为空。");
        }

        if (StringUtils.isEmpty(classifyName)) {
            throw new ValidateException("类目名称不能为空。");
        }

        PrizeClassifyEntity entity = service.getById(id);
        entity.setName(classifyName);
        service.saveOrUpdate(entity);
        return new TySuccessResponse(entity);
    }

    @Override
    @OperControllerLog(module = "奖品分类管理", type = "删除奖品分类")
    public TyResponse deletePrizeClassfy(@PathVariable Long id) {
        if (null == id) {
            throw new ValidateException("id不能为空。");
        }

        service.remove(new QueryWrapper<PrizeClassifyEntity>().lambda().eq(PrizeClassifyEntity::getId, id));
        return new TySuccessResponse(id);
    }
}
