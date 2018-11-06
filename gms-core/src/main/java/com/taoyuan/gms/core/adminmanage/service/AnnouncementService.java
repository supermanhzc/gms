package com.taoyuan.gms.core.adminmanage.service;


import com.taoyuan.gms.api.adminmanage.content.AnnouncementDto;
import com.taoyuan.gms.core.adminmanage.bo.AnnouncementBo;
import com.taoyuan.gms.core.adminmanage.dao.AnnouncementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AnnouncementService implements IAnnouncemnetService{

    @Autowired
    private AnnouncementMapper mapper;

    @Override
    public List<AnnouncementDto> getAnnouncements() {
        List<AnnouncementDto> dtos = new ArrayList<AnnouncementDto>();
        List<AnnouncementBo> bos = mapper.selectAll();
        for(AnnouncementBo bo:bos){
            dtos.add(convertBo2Dto(bo));
        }
        return dtos;
    }

    @Override
    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AnnouncementDto announcementDto) {
        return mapper.insert(convertDto2Bo(announcementDto));
    }

    @Override
    public AnnouncementDto getAnnouncement(Long id) {
        return convertBo2Dto(mapper.selectByPrimaryKey(id));
    }

    @Override
    public int updateAnnouncement(AnnouncementDto announcementDto) {
        return mapper.updateByPrimaryKey(convertDto2Bo(announcementDto));
    }

    private AnnouncementDto convertBo2Dto(AnnouncementBo bo){
        AnnouncementDto dto = new AnnouncementDto();
        dto.setId(bo.getId());
        dto.setSort(bo.getSort());
        dto.setTitle(bo.getTitle());
        dto.setContent(bo.getContent());
        return dto;
    }

    private AnnouncementBo convertDto2Bo(AnnouncementDto dto){
        AnnouncementBo bo = new AnnouncementBo();
        bo.setTitle(dto.getTitle());
        bo.setSort(dto.getSort());
        bo.setContent(dto.getContent());

        LocalDateTime localDateTime = LocalDateTime.now();
        String nowTime =localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        bo.setCreateTime(Timestamp.valueOf(nowTime).toString());
        bo.setCreateUser(Long.valueOf(1));
        bo.setUpdateTime(Timestamp.valueOf(nowTime).toString());
        bo.setUpdateUser(Long.valueOf(1));
        return bo;
    }
}
