package com.taoyuan.gms.core.adminmanage.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taoyuan.gms.api.adminmanage.bo.VerificationCodeBo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VerificationCodeMapper  extends BaseMapper<VerificationCodeBo> {

//    int insert(VerificationCodeBo verificationCodeBo);
//
//    List<VerificationCodeBo> selectAll();
}
