package com.vs.mapper.studentMapper.impl;

import com.vs.entity.student.Student;
import org.apache.ibatis.annotations.Param;

public interface StudentMapperImpl {

    Student chickStudent(@Param("student") Student student);

}
