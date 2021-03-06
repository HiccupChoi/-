package com.vs.controller;

import com.vs.entity.User;
import com.vs.result.Result;
import com.vs.service.UserService;
import com.vs.service.impl.UserServiceImpl;
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
    private UserService userService;


    @ResponseBody
    @RequestMapping(value = "/users/login", method = {RequestMethod.POST})
    public Result login(String user_code , String user_pwd, HttpServletRequest request){
        User user = new User();
        user.setUserCode(user_code);
        user.setUserPwd(user_pwd);
        Result result = userService.login(user);
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

    /**
     * 三码合一检测
     * @param userCode
     * @param UserName
     * @param activationCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkThreeCode")
    public Result checkThreeCode(String userCode,String UserName,String activationCode){
        return userService.checkThreeCode(userCode,UserName,activationCode);
    }

    @ResponseBody
    @RequestMapping(value = "/register",method = {RequestMethod.POST})
    public Result register(String userCode,String UserName,String UserPwd){
        return userService.register(userCode,UserName,UserPwd);
    }


}