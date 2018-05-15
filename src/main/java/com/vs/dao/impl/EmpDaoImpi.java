package com.vs.dao.impl;

import com.vs.dao.EmpDao;
import com.vs.entity.Student;
import com.vs.result.Result;
import org.springframework.stereotype.Component;

@Component("empDaoImpi")
public class EmpDaoImpi implements EmpDao {

    @Override
    public Result login(Student student) {
        return null;
    }
}
