package com.hejianzhang.dao;

import com.hejianzhang.model.regresscases;
import java.util.List;

public interface regresscasesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(regresscases record);

    int insertSelective(regresscases record);
    List<regresscases> selectAll();
    List<regresscases> selectMulIds(Long[] ids);
    List<regresscases> selectbyRegressuiteid(Integer regressuiteId);

    regresscases selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(regresscases record);

    int updateByPrimaryKey(regresscases record);
}