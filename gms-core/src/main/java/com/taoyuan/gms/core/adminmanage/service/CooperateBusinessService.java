package com.taoyuan.gms.core.adminmanage.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyuan.gms.api.adminmanage.content.CooperateBusinessDto;
import com.taoyuan.gms.core.adminmanage.bo.CooperateBusinessBo;
import com.taoyuan.gms.core.adminmanage.dao.CooperateBusinessMapper;
import com.taoyuan.gms.core.sitemanage.account.bo.AccountBo;
import com.taoyuan.gms.core.sitemanage.account.dao.AccountBoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CooperateBusinessService  extends ServiceImpl<CooperateBusinessMapper, CooperateBusinessBo> implements ICooperateBusinessService {

//    @Autowired
//    private CooperateBusinessMapper mapper;
//
//    @Override
//    public List<CooperateBusinessDto> getCooperateBusinesss() {
//        List<CooperateBusinessBo> bos = mapper.selectAll();
//        List<CooperateBusinessDto> dtos = new ArrayList<CooperateBusinessDto>();
//        for (CooperateBusinessBo bo : bos) {
//            dtos.add(convertBo2Dto(bo));
//        }
//        return dtos;
//    }
//
//    @Override
//    public int delete(Long id) {
//        return mapper.deleteByPrimaryKey(id);
//    }
//
//    @Override
//    public int insert(CooperateBusinessDto cooperateBusinessDto) {
//        return mapper.insert(convertDto2Bo(cooperateBusinessDto));
//    }
//
//    @Override
//    public CooperateBusinessDto getCooperateBusiness(Long id) {
//        return convertBo2Dto(mapper.selectByPrimaryKey(id));
//    }
//
//    @Override
//    public int updateCooperateBusiness(CooperateBusinessDto cooperateBusinessDto) {
//        return mapper.updateByPrimaryKey(convertDto2Bo(cooperateBusinessDto));
//    }
//
//    private CooperateBusinessDto convertBo2Dto(CooperateBusinessBo bo) {
//        CooperateBusinessDto dto = new CooperateBusinessDto();
//        dto.setId(bo.getId());
//        dto.setName(bo.getName());
//        dto.setQq(bo.getQq());
//        return dto;
//    }
//
//    private CooperateBusinessBo convertDto2Bo(CooperateBusinessDto dto){
//        CooperateBusinessBo bo = new CooperateBusinessBo();
//        bo.setId(dto.getId());
//        bo.setName(dto.getName());
//        bo.setQq(dto.getQq());
//        return bo;
//    }
}
