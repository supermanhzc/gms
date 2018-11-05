package com.taoyuan.gms.core.adminmanage.dao;

import com.taoyuan.gms.core.adminmanage.bo.CooperateBusinessBo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CooperateBusinessMapper {

    int deleteByPrimaryKey(Long id);

    int insert(CooperateBusinessBo cooperateBusinessBo);

    int insertSelective(CooperateBusinessBo cooperateBusinessBo);

    CooperateBusinessBo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CooperateBusinessBo cooperateBusinessBo);

    int updateByPrimaryKey(CooperateBusinessBo cooperateBusinessBo);

    List<CooperateBusinessBo> selectAll();
}
