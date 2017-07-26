package com.hejianzhang.dao;

import com.hejianzhang.model.exResults;
import java.util.List;



public interface exResultsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(exResults record);

    int insertSelective(exResults record);

    exResults selectByPrimaryKey(Integer id);
    exResults selectBytimeandSceneid(String updatetime,String sceneid);
    List<exResults> selectAll();

    int updateByPrimaryKeySelective(exResults record);

    int updateByPrimaryKey(exResults record);
}