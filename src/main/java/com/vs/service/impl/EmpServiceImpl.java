package com.vs.service.impl;

import com.vs.dao.EmpDao;
import com.vs.entity.Student;
import com.vs.result.Result;
import com.vs.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "empService")
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDao empDao;

    public Result login(Student student){
        return empDao.login(student);
    }

}
