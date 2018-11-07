package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.taoyuan.gms.api.admin.ContentApi;
import com.taoyuan.gms.core.adminmanage.service.IAnnouncemnetService;
import com.taoyuan.gms.core.adminmanage.service.ICooperateBusinessService;
import com.taoyuan.gms.model.entity.admin.content.AnnouncementEntity;
import com.taoyuan.gms.model.entity.admin.content.CooperateBusinessEntity;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class ContentController implements ContentApi {

    @Autowired
    private IAnnouncemnetService announcemnetService;

    @Autowired
    private ICooperateBusinessService cooperateBusinessService;

    @Override
    public IPage<Map<String, Object>> getAnnouncements(Integer pageIndex, Integer pageSize) {
//        throw TyExceptionUtil.buildException(100, "异常测试");
        return announcemnetService.pageMaps(new Page<AnnouncementEntity>(pageIndex, pageSize), null);
    }

    @Override
    public Map<String, Object> getAnnouncement(Long id) {
        return announcemnetService.getMap(new QueryWrapper<AnnouncementEntity>().eq("id", id));
    }

    @Override
    public void createAnnouncement(AnnouncementEntity announcement) {
        announcemnetService.save(announcement);
    }

    @Override
    public void modifyAnnouncement(AnnouncementEntity announcement) {
        announcemnetService.update(announcement, null);
    }

    @Override
    public void deleteAnnouncement(Long id) {
        announcemnetService.remove(new QueryWrapper<AnnouncementEntity>().eq("id", id));
    }

    @Override
    public IPage<Map<String, Object>> getCooperateBusinesss() {
        return cooperateBusinessService.pageMaps(new Page<CooperateBusinessEntity>(1, 10), null);
    }

    @Override
    public Map<String, Object> getCooperateBusiness(Long id) {
        return cooperateBusinessService.getMap(new QueryWrapper<CooperateBusinessEntity>().eq("id", id));
    }

    @Override
    public void createCooperateBusiness(CooperateBusinessEntity cooperateBusiness) {
        cooperateBusinessService.save(cooperateBusiness);
    }

    @Override
    public void modifyCooperateBusiness(CooperateBusinessEntity cooperateBusiness) {
        cooperateBusinessService.update(cooperateBusiness, null);
    }

    @Override
    public void deleteCooperateBusiness(Long id) {
        cooperateBusinessService.remove(new QueryWrapper<CooperateBusinessEntity>().eq("id", id));
    }
}
