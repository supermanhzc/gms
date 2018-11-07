package com.taoyuan.gms.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.gms.model.entity.admin.content.AnnouncementEntity;
import com.taoyuan.gms.model.entity.admin.content.CooperateBusinessEntity;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
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
    public IPage<Map<String, Object>> getAnnouncements(@PathVariable("pageIndex") Integer pageIndex,@PathVariable("pageSize") Integer pageSize);

    /**
     * 根据id查询公告
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/announcement/{id}", method = RequestMethod.GET)
    public Map<String, Object> getAnnouncement(@PathVariable("id") Long id);

    /**
     * 创建公告
     *
     * @param announcement
     */
    @RequestMapping(value = "/announcement", method = RequestMethod.POST)
    public void createAnnouncement(AnnouncementEntity announcement);

    /**
     * 修改公告
     *
     * @param announcement
     */
    @RequestMapping(value = "/announcement/{id}", method = RequestMethod.PUT)
    public void modifyAnnouncement(AnnouncementEntity announcement);

    /**
     * 删除公告
     *
     * @param id
     */
    @RequestMapping(value = "/announcement/{id}", method = RequestMethod.DELETE)
    public void deleteAnnouncement(@PathVariable("id") Long id);

    /**
     * 查询所有合作商家
     *
     * @return
     */
    @RequestMapping(value = "/cooperatbusiness", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getCooperateBusinesss();

    /**
     * 根据id查询合作商家
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/cooperatbusiness/{id}", method = RequestMethod.GET)
    public Map<String, Object> getCooperateBusiness(@PathVariable("id") Long id);

    /**
     * 创建合作商家
     *
     * @param cooperateBusiness
     */
    @RequestMapping(value = "/cooperatbusiness", method = RequestMethod.POST)
    public void createCooperateBusiness(CooperateBusinessEntity cooperateBusiness);

    /**
     * 修改合作商家
     *
     * @param cooperateBusiness
     */
    @RequestMapping(value = "/cooperatbusiness/{id}", method = RequestMethod.PUT)
    public void modifyCooperateBusiness(CooperateBusinessEntity cooperateBusiness);

    /**
     * 删除合作商家
     *
     * @param id
     */
    @RequestMapping(value = "/cooperatbusiness/{id}", method = RequestMethod.DELETE)
    public void deleteCooperateBusiness(@PathVariable("id") Long id);
}
