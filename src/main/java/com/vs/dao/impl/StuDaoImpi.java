package com.vs.dao.impl;

import com.vs.dao.StuDao;
import com.vs.entity.Student;
import com.vs.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("empDao")
public class StuDaoImpi implements StuDao {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student login(Student student) {
        return studentMapper.chickStudent(student);
    }
}
