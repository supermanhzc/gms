package com.taoyuan.gms.core.admin.service;

import com.taoyuan.gms.api.admin.contentmgnt.AnnouncementDto;
import com.taoyuan.gms.core.admin.bo.AnnouncementBo;

import java.util.List;

public interface IAnnouncemnetService {

    List<AnnouncementDto> getAnnouncements();

    int delete(Long id);

    int insert(AnnouncementDto announcementDto);

    AnnouncementDto getAnnouncement(Long id);

    int updateAnnouncement(AnnouncementDto announcementDto);
}
