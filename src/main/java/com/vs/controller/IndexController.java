package com.vs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {


    @RequestMapping(value = "/")
    public String ToIndex(Model model, HttpServletRequest request){

        if (request.getSession().getAttribute("user") != null){
            model.addAttribute("user",request.getSession().getAttribute("user"));
        }
        return "index";
    }


}
