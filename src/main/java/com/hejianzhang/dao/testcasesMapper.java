package com.hejianzhang.dao;

import com.hejianzhang.model.testcases;

public interface testcasesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(testcases record);

    int insertSelective(testcases record);

    testcases selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(testcases record);

    int updateByPrimaryKey(testcases record);
}