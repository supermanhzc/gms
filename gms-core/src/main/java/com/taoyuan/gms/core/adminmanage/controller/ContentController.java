package com.taoyuan.gms.core.adminmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.taoyuan.framework.bs.aspect.OperControllerLog;
import com.taoyuan.framework.common.exception.ValidateException;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.framework.common.http.TySession;
import com.taoyuan.framework.common.http.TySuccessResponse;
import com.taoyuan.gms.api.admin.ContentApi;
import com.taoyuan.gms.core.adminmanage.service.IAnnouncemnetService;
import com.taoyuan.gms.core.adminmanage.service.ICooperateBusinessService;
import com.taoyuan.gms.model.entity.admin.content.AnnouncementEntity;
import com.taoyuan.gms.model.entity.admin.content.CooperateBusinessEntity;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    @OperControllerLog(module = "公告管理", type = "分页查询公告")
    public TyResponse getAnnouncements(@PathVariable Integer pageIndex, @PathVariable Integer pageSize) {
        log.info("input is pageIndex:{} pageSize:{}", pageIndex, pageSize);
        return new TySuccessResponse(announcemnetService.pageMaps(new Page<AnnouncementEntity>(pageIndex, pageSize),
                null));
    }

    @Override
    @OperControllerLog(module = "公告管理", type = "根据id查询公告")
    public TyResponse getAnnouncement(@PathVariable Long id) {
        if (null == id) {
            throw new ValidateException("id不能为空。");
        }
        return new TySuccessResponse(announcemnetService.getMap(new QueryWrapper<AnnouncementEntity>().eq("id", id)));
    }

    @Override
    @OperControllerLog(module = "公告管理", type = "创建公告")
    public TyResponse createAnnouncement(@RequestBody AnnouncementEntity announcement) {
        log.info("input:{}", announcement);
        if (null == announcement.getTitle()) {
            throw new ValidateException("标题不能为空。");
        }

        if (null == announcement.getSort()) {
            throw new ValidateException("排序不能为空。");
        }

        announcement.setCreateUser(TySession.getCurrentUser().getUserId());
        announcement.setCreateTime(new Date());
        announcemnetService.save(announcement);
        return new TySuccessResponse(announcement);
    }

    @Override
    @OperControllerLog(module = "公告管理", type = "修改公告")
    public TyResponse modifyAnnouncement(@RequestBody AnnouncementEntity announcement) {
        Long id = announcement.getId();
        if (null == id) {
            throw new ValidateException("id不能为空。");
        }

        AnnouncementEntity orgData =
                (AnnouncementEntity) announcemnetService.getObj(new QueryWrapper<AnnouncementEntity>().eq("id",
                        id));
        if (null == orgData) {
            throw new ValidateException("对象不存在。");
        }

        orgData.setUpdateTime(new Date());
        orgData.setUpdateUser(TySession.getCurrentUser().getUserId());
        if (announcement.getContent() != null) {
            orgData.setContent(announcement.getContent());
        }
        if (announcement.getSort() != null) {
            orgData.setSort(announcement.getSort());
        }

        if (announcement.getTitle() != null) {
            orgData.setTitle(announcement.getTitle());
        }
        announcemnetService.saveOrUpdate(orgData);
        return new TySuccessResponse(orgData);
    }

    @Override
    @OperControllerLog(module = "公告管理", type = "删除公告")
    public TyResponse deleteAnnouncement(@PathVariable Long id) {
        if (null == id) {
            throw new ValidateException("id不能为空。");
        }
        log.info("delete input:{}", id);
        announcemnetService.remove(new QueryWrapper<AnnouncementEntity>().eq("id", id));
        return new TySuccessResponse(id);
    }

    @Override
    @OperControllerLog(module = "合作商家管理", type = "查询合作商家")
    public TyResponse getCooperateBusinesss() {
        return new TySuccessResponse(cooperateBusinessService.pageMaps(new Page<>(), null));
    }

    @Override
    @OperControllerLog(module = "合作商家管理", type = "根据id查询合作商家")
    public TyResponse getCooperateBusiness(@PathVariable Long id) {
        Map<String, Object> rslt = cooperateBusinessService.getMap(new QueryWrapper<CooperateBusinessEntity>().eq("id",
                id));
        return new TySuccessResponse(rslt);
    }

    @Override
    @OperControllerLog(module = "合作商家管理", type = "创建合作商家")
    public TyResponse createCooperateBusiness(@RequestBody CooperateBusinessEntity cooperateBusiness) {
        if (null == cooperateBusiness.getName()) {
            throw new ValidateException("名称不能为空。");
        }

        if (null == cooperateBusiness.getQq()) {
            throw new ValidateException("QQ不能为空。");
        } else {
            String qq = cooperateBusiness.getQq();
            if (!qq.matches("[1-9][0-9]{4,14}")) {
                throw new ValidateException("QQ格式非法。");
            }
        }
        cooperateBusiness.setCreateTime(new Date());
        cooperateBusiness.setCreateUser(TySession.getCurrentUser().getUserId());
        cooperateBusinessService.save(cooperateBusiness);
        log.info("input:{},id:{}", cooperateBusiness, cooperateBusiness.getId());
        return new TySuccessResponse(cooperateBusiness);
    }

    @Override
    @OperControllerLog(module = "合作商家管理", type = "修改合作商家")
    public TyResponse modifyCooperateBusiness(@RequestBody CooperateBusinessEntity cooperateBusiness) {
        if (null == cooperateBusiness.getId()) {
            throw new ValidateException("id不能为空。");
        }

        CooperateBusinessEntity entity =
                (CooperateBusinessEntity) cooperateBusinessService.getObj(new QueryWrapper<CooperateBusinessEntity>().eq("id", cooperateBusiness.getId()));
        if (cooperateBusiness.getName() != null) {
            entity.setName(cooperateBusiness.getName());
        }

        if (cooperateBusiness.getQq() != null) {
            entity.setQq(cooperateBusiness.getQq());
        }
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(TySession.getCurrentUser().getUserId());
        cooperateBusinessService.saveOrUpdate(entity);
        return new TySuccessResponse(entity);
    }

    @Override
    @OperControllerLog(module = "合作商家管理", type = "删除合作商家")
    public TyResponse deleteCooperateBusiness(@PathVariable Long id) {
        if (null == id) {
            throw new ValidateException("id不能为空。");
        }
        cooperateBusinessService.remove(new QueryWrapper<CooperateBusinessEntity>().eq("id", id));
        return new TySuccessResponse(id);
    }
}
