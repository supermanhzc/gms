package com.taoyuan.gms.api.admin;

import com.taoyuan.framework.common.entity.TyPageEntity;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.entity.admin.web.LevelSettingEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(value = "等级服务")
@RequestMapping("/levelmgnt")
public interface LevelSettingApi {

    @RequestMapping(value = "/retrieve", method = RequestMethod.POST)
    public TyResponse retrieve(@RequestBody TyPageEntity pageEntity);

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public TyResponse update(@RequestBody List<LevelSettingEntity> levelSettingEntityList);
}
