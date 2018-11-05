package com.taoyuan.gms.core.admin.service;

import com.taoyuan.gms.api.admin.contentmgnt.CooperateBusinessDto;
import com.taoyuan.gms.core.admin.bo.AnnouncementBo;
import com.taoyuan.gms.core.admin.bo.CooperateBusinessBo;

import java.util.List;

public interface ICooperateBusinessService {

    List<CooperateBusinessDto> getCooperateBusinesss();

    int delete(Long id);

    int insert(CooperateBusinessDto cooperateBusinessDto);

    CooperateBusinessDto getCooperateBusiness(Long id);

    int updateCooperateBusiness(CooperateBusinessDto cooperateBusinessDto);
}
