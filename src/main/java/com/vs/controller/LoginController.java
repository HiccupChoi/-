package com.vs.controller;

import com.vs.entity.User;
import com.vs.result.Result;
import com.vs.service.UserService;
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
    private UserService empService;

    @ResponseBody
    @RequestMapping(value = "/users/login", method = {RequestMethod.POST})
    public Result login(String user_code , String user_pwd, HttpServletRequest request){
        User user = new User();
        user.setUserCode(user_code);
        user.setUserPwd(user_pwd);
        Result result = empService.login(user);
        if (result.getData() != null){
            user = (User) result.getData();
            request.setAttribute("user",user);
            request.getSession().setAttribute("user", user);
        }
        return result;
    }

    @RequestMapping(value = "/users/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "redirect:../";
    }
}