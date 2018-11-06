package com.taoyuan.gms.api.adminmanage.content;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Api(value = "公告服务")
@RequestMapping("/contentmgnt")
public interface ContentApi {
    /**
     * 查询所有公告
     *
     * @return
     */
    @RequestMapping(value = "/announcement", method = RequestMethod.GET)
    public IPage<Map<String, Object>> getAnnouncements();

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
    public void createAnnouncement(AnnouncementDto announcement);

    /**
     * 修改公告
     *
     * @param announcement
     */
    @RequestMapping(value = "/announcement/{id}", method = RequestMethod.PUT)
    public void modifyAnnouncement(AnnouncementDto announcement);

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
    public void createCooperateBusiness(CooperateBusinessDto cooperateBusiness);

    /**
     * 修改合作商家
     *
     * @param cooperateBusiness
     */
    @RequestMapping(value = "/cooperatbusiness/{id}", method = RequestMethod.PUT)
    public void modifyCooperateBusiness(CooperateBusinessDto cooperateBusiness);

    /**
     * 删除合作商家
     *
     * @param id
     */
    @RequestMapping(value = "/cooperatbusiness/{id}", method = RequestMethod.DELETE)
    public void deleteCooperateBusiness(@PathVariable("id") Long id);
}
