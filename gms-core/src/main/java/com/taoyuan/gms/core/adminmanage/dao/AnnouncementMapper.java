package com.taoyuan.gms.core.adminmanage.dao;

import com.taoyuan.gms.core.adminmanage.bo.AnnouncementBo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnouncementMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AnnouncementBo announcementBo);

    int insertSelective(AnnouncementBo announcementBo);

    AnnouncementBo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AnnouncementBo announcementBo);

    int updateByPrimaryKey(AnnouncementBo announcementBo);

    List<AnnouncementBo> selectAll();
}
