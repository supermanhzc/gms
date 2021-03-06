package com.taoyuan.gms.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.entity.admin.content.AnnouncementEntity;
import com.taoyuan.gms.model.entity.admin.content.CooperateBusinessEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Api(value = "公告服务")
@RequestMapping("/contentmgnt")
public interface ContentApi {
    /**
     * 查询所有公告
     *
     * @return
     */
    @RequestMapping(value = "/announcement/page/index={pageIndex}&size={pageSize}", method = RequestMethod.GET)
    public TyResponse getAnnouncements(@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize);

    /**
     * 根据id查询公告
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/announcement/{id}", method = RequestMethod.GET)
    public TyResponse getAnnouncement(@PathVariable("id") Long id);

    /**
     * 创建公告
     *
     * @param announcement
     */
    @RequestMapping(value = "/announcement", method = RequestMethod.POST)
    public TyResponse createAnnouncement(@RequestBody AnnouncementEntity announcement);

    /**
     * 修改公告
     *
     * @param announcement
     */
    @RequestMapping(value = "/announcement", method = RequestMethod.PUT)
    public TyResponse modifyAnnouncement(@RequestBody AnnouncementEntity announcement);

    /**
     * 删除公告
     *
     * @param id
     */
    @RequestMapping(value = "/announcement/{id}", method = RequestMethod.DELETE)
    public TyResponse deleteAnnouncement(@PathVariable("id") Long id);

    /**
     * 查询所有合作商家
     *
     * @return
     */
    @RequestMapping(value = "/cooperatbusiness", method = RequestMethod.GET)
    public TyResponse getCooperateBusinesss();

    /**
     * 根据id查询合作商家
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/cooperatbusiness/{id}", method = RequestMethod.GET)
    public TyResponse getCooperateBusiness(@PathVariable("id") Long id);

    /**
     * 创建合作商家
     *
     * @param cooperateBusiness
     */
    @RequestMapping(value = "/cooperatbusiness", method = RequestMethod.POST)
    public TyResponse createCooperateBusiness(@RequestBody CooperateBusinessEntity cooperateBusiness);

    /**
     * 修改合作商家
     *
     * @param cooperateBusiness
     */
    @RequestMapping(value = "/cooperatbusiness", method = RequestMethod.PUT)
    public TyResponse modifyCooperateBusiness(@RequestBody CooperateBusinessEntity cooperateBusiness);

    /**
     * 删除合作商家
     *
     * @param id
     */
    @RequestMapping(value = "/cooperatbusiness/{id}", method = RequestMethod.DELETE)
    public TyResponse deleteCooperateBusiness(@PathVariable("id") Long id);
}
