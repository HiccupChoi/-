package com.vs.controller;

import com.vs.entity.User;
import com.vs.result.Result;
import com.vs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/createUser")
    public Result createUser(){
        User user = new User();
        user.setUserCode("12014000004");
        user.setAuthority("1");
        user.setUserName("提提");
        user.setClassId(1417);
        user.setGender("2");
        return userService.createUser(user);
    }


    @RequestMapping("/cancelUser")
    public Result cancelUser(){
        return userService.cancelUser("12014006014");
    }
}
