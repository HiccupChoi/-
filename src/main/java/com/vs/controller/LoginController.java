package com.vs.controller;

import com.vs.entity.Student;
import com.vs.result.Result;
import com.vs.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
public class LoginController {

    @Autowired
    private StuService empService;

    @ResponseBody
    @RequestMapping(value = "/users/login", method = {RequestMethod.POST})
    public Result login(String stu_name , String stu_pwd, HttpServletRequest request){
        Student student = new Student();
        student.setStuName(stu_name);
        student.setStuPwd(stu_pwd);
        student = (Student) empService.login(student).getData();
        request.getSession().setAttribute("user", student);
        return empService.login(student);
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "index";
    }
}