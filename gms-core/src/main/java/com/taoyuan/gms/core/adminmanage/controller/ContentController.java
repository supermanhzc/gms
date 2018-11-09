package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.admin.ContentApi;
import com.taoyuan.gms.core.adminmanage.service.IAnnouncemnetService;
import com.taoyuan.gms.core.adminmanage.service.ICooperateBusinessService;
import com.taoyuan.gms.model.entity.admin.content.AnnouncementEntity;
import com.taoyuan.gms.model.entity.admin.content.CooperateBusinessEntity;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Slf4j
@RestController
public class ContentController implements ContentApi {

    @Autowired
    private IAnnouncemnetService announcemnetService;

    @Autowired
    private ICooperateBusinessService cooperateBusinessService;

    @Override
    public TyResponse getAnnouncements(Integer pageIndex, Integer pageSize) {
        log.info("input is pageIndex:{} pageSize:{}", pageIndex, pageSize);
        return new TySuccessResponse(announcemnetService.pageMaps(new Page<AnnouncementEntity>(pageIndex, pageSize), null));
    }

    @Override
    public TyResponse getAnnouncement(Long id) {
        return new TySuccessResponse(announcemnetService.getMap(new QueryWrapper<AnnouncementEntity>().eq("id", id)));
    }

    @Override
    public TyResponse createAnnouncement(AnnouncementEntity announcement) {
        log.info("input:{}", announcement);
        announcement.setCreateTime(new Date());
        announcemnetService.save(announcement);
        return new TySuccessResponse(announcement);
    }

    @Override
    public TyResponse modifyAnnouncement(AnnouncementEntity announcement) {
        if (null == announcement.getId()) {
            throw new ValidateException(1000001, "参数不能为空。", "id");
        }
        announcemnetService.saveOrUpdate(announcement);
        return new TySuccessResponse(announcement);
    }

    @Override
    public TyResponse deleteAnnouncement(Long id) {
        log.info("delete input:{}", id);
        announcemnetService.remove(new QueryWrapper<AnnouncementEntity>().eq("id", id));
        return new TySuccessResponse(id);
    }

    @Override
    public TyResponse getCooperateBusinesss() {
        return new TySuccessResponse(cooperateBusinessService.pageMaps(new Page<CooperateBusinessEntity>(1, 10), null));
    }

    @Override
    public TyResponse getCooperateBusiness(Long id) {
        log.info("query value:",cooperateBusinessService.getMap(new QueryWrapper<CooperateBusinessEntity>().eq("id", id)));
        return new TySuccessResponse(cooperateBusinessService.getMap(new QueryWrapper<CooperateBusinessEntity>().eq("id", id)));
    }

    @Override
    public TyResponse createCooperateBusiness(CooperateBusinessEntity cooperateBusiness) {
        cooperateBusiness.setCreateTime(new Date());
        cooperateBusinessService.save(cooperateBusiness);
        log.info("input:{},id:{}", cooperateBusiness, cooperateBusiness.getId());
        return new TySuccessResponse(cooperateBusiness);
    }

    @Override
    public TyResponse modifyCooperateBusiness(CooperateBusinessEntity cooperateBusiness) {
        cooperateBusinessService.saveOrUpdate(cooperateBusiness);
        return new TySuccessResponse(cooperateBusiness);
    }

    @Override
    public TyResponse deleteCooperateBusiness(Long id) {
        cooperateBusinessService.remove(new QueryWrapper<CooperateBusinessEntity>().eq("id", id));
        return new TySuccessResponse(id);
    }
}
