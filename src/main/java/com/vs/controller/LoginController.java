package com.vs.controller;

import com.vs.entity.Student;
import com.vs.result.Result;
import com.vs.service.StuService;
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
    private StuService empService;

    @RequestMapping(value = "/users/login", method = {RequestMethod.POST})
    public Result login(String stu_name , String stu_pwd){
        Student student = new Student();
        student.setStuName(stu_name);
        student.setStuPwd(stu_pwd);

        return empService.login(student);
    }

}