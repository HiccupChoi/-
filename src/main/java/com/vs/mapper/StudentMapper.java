package com.vs.mapper;

import com.vs.entity.Student;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper{
    int deleteByPrimaryKey(Integer stuId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer stuId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    Student chickStudent(@Param("student") Student student);
}