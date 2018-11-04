package com.taoyuan.gms.core.sitemanage.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.taoyuan.gms.core.sitemanage.account.bo.AccountBo;

@Mapper
public interface AccountBoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AccountBo accountBo);

    int insertSelective(AccountBo accountBo);

    AccountBo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccountBo accountBo);

    int updateByPrimaryKey(AccountBo accountBo);
    
    List<AccountBo> selectAll();
}
