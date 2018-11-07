package com.taoyuan.gms.core.adminmanage.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.core.adminmanage.dao.AnnouncementMapper;
import com.taoyuan.gms.model.entity.admin.content.AnnouncementEntity;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, AnnouncementEntity> implements IAnnouncemnetService{

}
