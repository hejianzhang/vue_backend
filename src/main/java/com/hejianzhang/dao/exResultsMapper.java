package com.hejianzhang.dao;

import com.hejianzhang.model.exResults;




public interface exResultsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(exResults record);

    int insertSelective(exResults record);

    exResults selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(exResults record);

    int updateByPrimaryKey(exResults record);
}