package com.vs.controller;

import com.vs.entity.Student;
import com.vs.mapper.StudentMapper;
import com.vs.result.Result;
import com.vs.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/8/16.
 */
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @Autowired
    private StudentMapper studentMapper;

    @RequestMapping(value = "/users/login", method = {RequestMethod.POST})
    public Result login(String stu_name , String stu_pwd){
        Student student = new Student();
        student.setStuName(stu_name);
        student.setStuPwd(stu_pwd);
        studentMapper.chickStudent(student);

        return empService.login(student);
    }

}