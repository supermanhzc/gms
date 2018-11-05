package com.taoyuan.gms.core.admin.service;

import com.taoyuan.gms.api.admin.contentmgnt.AnnouncementDto;
import com.taoyuan.gms.core.admin.bo.AnnouncementBo;
import com.taoyuan.gms.core.admin.dao.AnnouncementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        bo.setId(dto.getId());
        bo.setTitle(dto.getTitle());
        bo.setSort(dto.getSort());
        bo.setContent(dto.getContent());
        return bo;
    }
}
