package com.hejianzhang.dao;

import com.hejianzhang.model.testresults;

public interface testresultsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(testresults record);

    int insertSelective(testresults record);

    testresults selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(testresults record);

    int updateByPrimaryKey(testresults record);
}