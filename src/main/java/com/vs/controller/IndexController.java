package com.vs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {


    @RequestMapping(value = "/")
    public String ToIndex(Model model){
        model.addAttribute("name","123");
        return "index";
    }
}
