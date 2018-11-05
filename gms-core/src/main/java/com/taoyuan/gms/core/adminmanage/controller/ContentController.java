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
    public AnnouncementDto getAnnouncement(long id) {
        return announcemnetService.getAnnouncement(id);
    }

    @Override
    public void createAnnouncement(String title, String sort, String content) {
        AnnouncementDto dto = new AnnouncementDto();
        dto.setTitle(title);
        dto.setSort(sort);
        dto.setContent(content);
        announcemnetService.updateAnnouncement(dto);
    }

    @Override
    public void modifyAnnouncement(String id, String title, String sort, String content) {
        AnnouncementDto dto = new AnnouncementDto();
        dto.setTitle(title);
        dto.setSort(sort);
        dto.setContent(content);
        announcemnetService.updateAnnouncement(dto);
    }

    @Override
    public void deleteAnnouncement(long id) {
        announcemnetService.delete(id);
    }

    @Override
    public List<CooperateBusinessDto> getCooperateBusinesss() {
        return cooperateBusinessService.getCooperateBusinesss();
    }

    @Override
    public CooperateBusinessDto getCooperateBusiness(long id) {
        return cooperateBusinessService.getCooperateBusiness(id);
    }

    @Override
    public void createCooperateBusiness(String name, String qq) {
        CooperateBusinessDto dto = new CooperateBusinessDto();
        dto.setName(name);
        dto.setQq(qq);
        cooperateBusinessService.insert(dto);
    }

    @Override
    public void modifyCooperateBusiness(String id, String name, String qq) {
        CooperateBusinessDto dto = new CooperateBusinessDto();
        dto.setName(name);
        dto.setQq(qq);
        cooperateBusinessService.updateCooperateBusiness(dto);
    }

    @Override
    public void deleteCooperateBusiness(long id) {
        cooperateBusinessService.delete(id);
    }

}
