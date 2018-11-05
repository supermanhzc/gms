package com.taoyuan.gms.core.adminmanage.service;


import com.taoyuan.gms.api.adminmanage.contentmgnt.AnnouncementDto;

import java.util.List;

public interface IAnnouncemnetService {

    List<AnnouncementDto> getAnnouncements();

    int delete(Long id);

    int insert(AnnouncementDto announcementDto);

    AnnouncementDto getAnnouncement(Long id);

    int updateAnnouncement(AnnouncementDto announcementDto);
}
