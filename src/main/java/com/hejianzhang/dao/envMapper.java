package com.hejianzhang.dao;

import com.hejianzhang.model.env;


import java.util.List;

public interface envMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(env record);

    int insertSelective(env record);

    List<env> selectAll();

    env selectByPrimaryKey(Integer id);

    env selectByName(String envname);

    int updateByPrimaryKeySelective(env record);

    int updateByPrimaryKey(env record);
}