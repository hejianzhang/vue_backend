package com.hejianzhang.dao;

import com.hejianzhang.model.regress;
import java.util.List;

public interface regressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(regress record);

    int insertSelective(regress record);

    List<regress> selectAll();

    regress selectByPrimaryKey(Integer id);

    List<Integer>  buildVersionByregressid(Integer regressid);

    int updateByPrimaryKeySelective(regress record);

    int updateByPrimaryKey(regress record);
}