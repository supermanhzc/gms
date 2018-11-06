package com.taoyuan.gms.core.adminmanage.controller;

import com.taoyuan.gms.api.adminmanage.content.AnnouncementDto;
import com.taoyuan.gms.api.adminmanage.content.ContentApi;
import com.taoyuan.gms.api.adminmanage.content.CooperateBusinessDto;
import com.taoyuan.gms.core.adminmanage.service.IAnnouncemnetService;
import com.taoyuan.gms.core.adminmanage.service.ICooperateBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContentController implements ContentApi {

    @Autowired
    private IAnnouncemnetService announcemnetService;

    @Autowired
    private ICooperateBusinessService cooperateBusinessService;

    @Override
    public List<AnnouncementDto> getAnnouncements() {
        return announcemnetService.getAnnouncements();
    }

    @Override
    public AnnouncementDto getAnnouncement(Long id) {
        return announcemnetService.getAnnouncement(id);
    }

    @Override
    public void createAnnouncement(AnnouncementDto announcement) {
        announcemnetService.insert(announcement);
    }

    @Override
    public void modifyAnnouncement(AnnouncementDto announcement) {
        announcemnetService.updateAnnouncement(announcement);
    }

    @Override
    public void deleteAnnouncement(Long id) {
        announcemnetService.delete(id);
    }

    @Override
    public List<CooperateBusinessDto> getCooperateBusinesss() {
        return cooperateBusinessService.getCooperateBusinesss();
    }

    @Override
    public CooperateBusinessDto getCooperateBusiness(Long id) {
        return cooperateBusinessService.getCooperateBusiness(id);
    }

    @Override
    public void createCooperateBusiness(CooperateBusinessDto cooperateBusiness) {
        cooperateBusinessService.insert(cooperateBusiness);
    }

    @Override
    public void modifyCooperateBusiness(CooperateBusinessDto cooperateBusiness) {
        cooperateBusinessService.updateCooperateBusiness(cooperateBusiness);
    }

    @Override
    public void deleteCooperateBusiness(Long id) {
        cooperateBusinessService.delete(id);
    }

}
