package com.vs.dao;

import com.vs.entity.Student;
import com.vs.result.Result;

public interface EmpDao {
    public Result login(Student student);
}
