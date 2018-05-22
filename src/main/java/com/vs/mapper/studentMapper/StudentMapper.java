package com.vs.mapper.studentMapper;

import com.vs.entity.student.Student;
import com.vs.mapper.studentMapper.impl.StudentMapperImpl;

public interface StudentMapper extends StudentMapperImpl {
    int deleteByPrimaryKey(Integer stuId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer stuId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}