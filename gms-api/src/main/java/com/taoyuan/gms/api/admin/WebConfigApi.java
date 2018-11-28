package com.taoyuan.gms.api.admin;


import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.entity.admin.content.AnnouncementEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "网站配置")
@RequestMapping("/webconfig")
public interface WebConfigApi {

    /**
     * 查询网站配置信息
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public TyResponse getConfig();

    /**
     * 查询网站配置信息
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public TyResponse saveConfig(@RequestBody AnnouncementEntity announcement);

}
