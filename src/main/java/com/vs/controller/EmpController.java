package com.vs.controller;

import com.github.pagehelper.PageInfo;
import com.vs.entity.Emp;
import com.vs.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
public class EmpController {


    @Autowired
    private EmpService empService;

    @ResponseBody
    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public int addEmp(Emp emp){
        return empService.addEmp(emp);
    }

    @ResponseBody
    @RequestMapping(value = "/getEmpList", method = {RequestMethod.GET})
    public PageInfo<Emp> getEmpList(Integer pageNum, Integer pageSize){
        pageNum = pageNum == null?0:pageNum;
        pageSize = pageSize == null?10:pageSize;
        return empService.findAllEmp(pageNum,pageSize);
    }

    @RequestMapping("/home")
    public String toIndex(Model model){
        model.addAttribute("name","<b>张三</b>");
        return "home1";
    }

}