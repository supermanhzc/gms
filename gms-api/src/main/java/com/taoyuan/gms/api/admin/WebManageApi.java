package com.taoyuan.gms.api.admin;

import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.entity.admin.content.AnnouncementEntity;
import com.taoyuan.gms.model.entity.admin.web.WebSettingEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Api(value = "网站管理")
@RequestMapping("/webmgnt")
public interface WebManageApi {
    @RequestMapping(value = "/setting", method = RequestMethod.GET)
    public Map<String, Object> getWebSetting();

    /**
     * 修改网站配置
     *
     * @param webSetting
     */
    @RequestMapping(value = "/setting", method = RequestMethod.PUT)
    public WebSettingEntity updateWebSetting(@RequestBody WebSettingEntity webSetting);

}