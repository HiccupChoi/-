package com.vs.mapper;

import com.vs.entity.UserClass;

public interface UserClassMapper {
    int deleteByPrimaryKey(Integer classId);

    int insert(UserClass record);

    int insertSelective(UserClass record);

    UserClass selectByPrimaryKey(Integer classId);

    int updateByPrimaryKeySelective(UserClass record);

    int updateByPrimaryKey(UserClass record);
}