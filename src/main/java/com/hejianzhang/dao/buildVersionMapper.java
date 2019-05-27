package com.hejianzhang.dao;

import com.hejianzhang.model.buildVersion;

public interface buildVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(buildVersion record);

    int insertSelective(buildVersion record);

    buildVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(buildVersion record);

    int updateByPrimaryKey(buildVersion record);
}