package com.hejianzhang.dao;

import com.hejianzhang.model.regresscasesResult;
import com.hejianzhang.DTO.processDetailDTO;

import java.util.List;
import java.util.Set;

public interface regresscasesResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(regresscasesResult record);

    int insertSelective(regresscasesResult record);

    regresscasesResult selectByPrimaryKey(Integer id);

    List<regresscasesResult> selectByRegressId(Integer regressid);

    List<regresscasesResult> selectBybuildVersion(Integer buildVersion);

    List<processDetailDTO> showDetail(Integer regressid);


    int updateByPrimaryKeySelective(regresscasesResult record);

    int updateByPrimaryKey(regresscasesResult record);
}