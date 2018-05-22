package com.vs.service.impl;

import com.vs.dao.StuDao;
import com.vs.entity.student.Student;
import com.vs.result.Result;
import com.vs.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "empService")
public class StuServiceImpl implements StuService {

    @Autowired
    private StuDao empDao;

    public Result login(Student student){
        Student studentInfo = empDao.login(student);
        Result result = new Result();
        if (studentInfo != null){
            result.setStatus(0);
            result.setMsg("登录成功");
            result.setSuccess(true);
            result.setData(studentInfo);
        } else {
            result.setStatus(1);
            result.setMsg("登录失败");
            result.setSuccess(false);
            result.setData(null);
        }
        return result;
    }

}
