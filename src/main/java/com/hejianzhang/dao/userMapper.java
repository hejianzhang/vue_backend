package com.hejianzhang.dao;

import com.hejianzhang.model.user;
import org.apache.ibatis.annotations.Param;

public interface userMapper {
    int deleteByPrimaryKey(@Param("brokerid") String brokerid, @Param("userid") String userid, @Param("tradingday") String tradingday);

    int insert(user record);

    int insertSelective(user record);

    user selectByPrimaryKey(@Param("brokerid") String brokerid, @Param("userid") String userid, @Param("tradingday") String tradingday);

    int updateByPrimaryKeySelective(user record);

    int updateByPrimaryKey(user record);
}