package com.taoyuan.gms.core.adminmanage.dao;

import com.taoyuan.gms.core.adminmanage.bo.VerificationCodeBo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VerificationCodeMapper {

    int insert(VerificationCodeBo verificationCodeBo);

    List<VerificationCodeBo> selectAll();
}
