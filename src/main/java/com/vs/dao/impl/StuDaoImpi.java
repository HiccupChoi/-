package com.vs.dao.impl;

import com.vs.dao.StuDao;
import com.vs.entity.student.Student;
import com.vs.mapper.studentMapper.impl.StudentMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("empDao")
public class StuDaoImpi implements StuDao {

    @Autowired
    private StudentMapperImpl studentMapper;

    @Override
    public Student login(Student student) {
        return studentMapper.chickStudent(student);
    }
}
