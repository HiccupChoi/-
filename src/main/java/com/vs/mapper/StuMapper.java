package com.vs.mapper;

import com.vs.entity.Stu;

public interface StuMapper {
    int insert(Stu record);

    int insertSelective(Stu record);
}