package com.hejianzhang.dao;

import com.hejianzhang.model.testscene;

import java.util.List;

public interface testsceneMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(testscene record);

    int insertSelective(testscene record);
    List<testscene> selectAll();

    testscene selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(testscene record);

    int updateByPrimaryKey(testscene record);
}