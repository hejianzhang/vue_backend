package com.hejianzhang.dao;

import com.hejianzhang.model.regresscene;
import java.util.List;
import com.hejianzhang.DTO.regressceneDTO;

public interface regressceneMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(regresscene record);

    int insertSelective(regresscene record);

    List<regresscene> selectAll();

    regresscene selectByPrimaryKey(Integer id);

    regressceneDTO SceneDeatailBySuiteId(Integer regressuiteid);

    List<regresscene> selectByregressid(Integer regressid);

    int updateByPrimaryKeySelective(regresscene record);

    int updateByPrimaryKey(regresscene record);
}