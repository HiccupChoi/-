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
        Student studentInfo = empDao.login(student);
        Result result = new Result();
        if (studentInfo == null){
            result.setStatus(0);
            result.setMsg("登录成功");
            result.setSuccess(true);
            result.setData(studentInfo);
        } else {
        }
        return result;
    }

}
