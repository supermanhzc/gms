package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taoyuan.gms.api.adminmanage.content.AnnouncementDto;
import com.taoyuan.gms.api.adminmanage.content.ContentApi;
import com.taoyuan.gms.api.adminmanage.content.CooperateBusinessDto;
import com.taoyuan.gms.core.adminmanage.bo.AnnouncementBo;
import com.taoyuan.gms.core.adminmanage.bo.CooperateBusinessBo;
import com.taoyuan.gms.core.adminmanage.service.IAnnouncemnetService;
import com.taoyuan.gms.core.adminmanage.service.ICooperateBusinessService;
import com.taoyuan.gms.core.sitemanage.account.bo.AccountBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ContentController implements ContentApi {

    @Autowired
    private IAnnouncemnetService announcemnetService;

    @Autowired
    private ICooperateBusinessService cooperateBusinessService;

    @Override
    public IPage<Map<String, Object>> getAnnouncements() {
        return announcemnetService.pageMaps(new Page<AnnouncementBo>(1, 10), null);
    }

    @Override
    public Map<String, Object> getAnnouncement(Long id) {
        return announcemnetService.getMap(new QueryWrapper<AnnouncementBo>().eq("id", id));
    }

    @Override
    public void createAnnouncement(AnnouncementDto announcement) {
//        announcemnetService.insert(announcement);
    }

    @Override
    public void modifyAnnouncement(AnnouncementDto announcement) {
//        announcemnetService.updateAnnouncement(announcement);
    }

    @Override
    public void deleteAnnouncement(Long id) {
//        announcemnetService.delete(id);
    }

    @Override
    public IPage<Map<String, Object>> getCooperateBusinesss() {
        return cooperateBusinessService.pageMaps(new Page<CooperateBusinessBo>(1, 10), null);
    }

    @Override
    public Map<String, Object> getCooperateBusiness(Long id) {
        return cooperateBusinessService.getMap(new QueryWrapper<CooperateBusinessBo>().eq("id", id));
    }

    @Override
    public void createCooperateBusiness(CooperateBusinessDto cooperateBusiness) {
//        cooperateBusinessService.insert(cooperateBusiness);
    }

    @Override
    public void modifyCooperateBusiness(CooperateBusinessDto cooperateBusiness) {
//        cooperateBusinessService.updateCooperateBusiness(cooperateBusiness);
    }

    @Override
    public void deleteCooperateBusiness(Long id) {
//        cooperateBusinessService.delete(id);
    }

}
