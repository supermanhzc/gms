package com.taoyuan.gms.api.adminmanage.content;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(value = "公告服务")
@RequestMapping("/contentmgnt")
public interface ContentApi {
    /**
     * 查询所有公告
     * @return
     */
    @RequestMapping(value = "/announcement", method = RequestMethod.GET)
    public List<AnnouncementDto> getAnnouncements();

    /**
     * 根据id查询公告
     * @param id
     * @return
     */
    @RequestMapping(value = "/announcement/{id}", method = RequestMethod.GET)
    public AnnouncementDto getAnnouncement(long id);

    /**
     * 创建公告
     * @param announcement
     */
    @RequestMapping(value = "/announcement", method = RequestMethod.POST)
    public void createAnnouncement(AnnouncementDto announcement);

    /**
     * 修改公告
     * @param id
     * @param title
     * @param sort
     * @param content
     */
    @RequestMapping(value = "/announcement/{id}", method = RequestMethod.PUT)
    public void modifyAnnouncement(String id, String title, String sort, String content);

    /**
     * 删除公告
     * @param id
     */
    @RequestMapping(value = "/announcement/{id}", method = RequestMethod.DELETE)
    public void deleteAnnouncement(long id);

    /**
     * 查询所有合作商家
     * @return
     */
    @RequestMapping(value = "/cooperatbusiness", method = RequestMethod.GET)
    public List<CooperateBusinessDto> getCooperateBusinesss();

    /**
     * 根据id查询合作商家
     * @param id
     * @return
     */
    @RequestMapping(value = "/cooperatbusiness/{id}", method = RequestMethod.GET)
    public CooperateBusinessDto getCooperateBusiness(long id);

    /**
     * 创建合作商家
     * @param name
     * @param qq
     */
    @RequestMapping(value = "/cooperatbusiness/", method = RequestMethod.POST)
    public void createCooperateBusiness(String name, String qq);

    /**
     * 修改合作商家
     * @param id
     * @param name
     * @param qq
     */
    @RequestMapping(value = "/cooperatbusiness/{id}", method = RequestMethod.PUT)
    public void modifyCooperateBusiness(String id, String name, String qq);

    /**
     * 删除合作商家
     * @param id
     */
    @RequestMapping(value = "/cooperatbusiness/{id}", method = RequestMethod.DELETE)
    public void deleteCooperateBusiness(long id);
}
