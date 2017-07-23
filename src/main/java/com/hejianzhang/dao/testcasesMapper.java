package com.hejianzhang.dao;

import com.hejianzhang.model.testcases;

import java.util.List;

public interface testcasesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(testcases record);

    int insertSelective(testcases record);

    testcases selectByPrimaryKey(Integer id);
    List<testcases> selectAll();
    List<testcases> selectMulIds(Long[] ids);

    int updateByPrimaryKeySelective(testcases record);

    int updateByPrimaryKey(testcases record);
}