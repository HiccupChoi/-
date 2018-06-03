package com.vs.controller;

import com.vs.entity.Score;
import com.vs.entity.User;
import com.vs.result.Result;
import com.vs.service.ScoreService;
import com.vs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/createUser")
    public Result createUser(){
        User user = new User();
        user.setUserCode("12014000002");
        user.setAuthority("1");
        user.setUserName("哒哒");
        user.setClassId(1417);
        user.setGender("2");
        return userService.createUser(user);
    }


    @RequestMapping("/cancelUser")
    public Result cancelUser(){
        return userService.cancelUser("12014006014");
    }
}
