package com.vs.controller;

import com.vs.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/8/16.
 */
@RestController
public class EmpController {


    @Autowired
    private EmpService empService;



    @RequestMapping(value = "/users/login", method = {RequestMethod.POST})
    public String login(String stu_name , String stu_pwd){
        return stu_name + " " + stu_pwd;
    }

}