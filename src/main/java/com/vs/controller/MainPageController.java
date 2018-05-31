package com.vs.controller;

import com.vs.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainPageController {

    @RequestMapping("/homePage")
    public String toHomePage(HttpServletRequest request, Model model){
        User user = (User) request.getSession().getAttribute("user");
        if (user != null){
            model.addAttribute("user",user);
        }
        return "user";
    }

}
