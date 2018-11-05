package com.taoyuan.gms.core.adminmanage.service;


import com.taoyuan.gms.api.adminmanage.content.CooperateBusinessDto;

import java.util.List;

public interface ICooperateBusinessService {

    List<CooperateBusinessDto> getCooperateBusinesss();

    int delete(Long id);

    int insert(CooperateBusinessDto cooperateBusinessDto);

    CooperateBusinessDto getCooperateBusiness(Long id);

    int updateCooperateBusiness(CooperateBusinessDto cooperateBusinessDto);
}
