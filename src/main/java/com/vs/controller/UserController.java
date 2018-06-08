package com.vs.controller;

import com.vs.entity.User;
import com.vs.entity.UserClass;
import com.vs.result.Result;
import com.vs.service.ClassService;
import com.vs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ClassService classService;

    private Map<Integer,UserClass> userClassMap = new HashMap<>();

    @RequestMapping("/createUser")
    public Result createUser(String userName,String authority,String gender,String classId){
        User user = new User();
        user.setAuthority(authority);
        user.setUserName(userName);
        user.setClassId(Integer.parseInt(classId));
        user.setGender(gender);
        return userService.createUser(user);
    }


    @RequestMapping("/cancelUser")
    public Result cancelUser(String userCode, Model model){
        Result result = userService.cancelUser(userCode);
        List<UserClass> userClasses = classService.findAllClass();
        for (int i = 0; i < userClasses.size(); i++) {
            userClassMap.put(userClasses.get(i).getClassId(),userClasses.get(i));
        }
        model.addAttribute("userClasses",userClasses);
        if (userCode.substring(0,1).equals("1")
            || userCode.substring(0,1).equals("4")
            || userCode.substring(0,1).equals("5")){
            List<User> Student = userService.findUserByAuthority("1");
            for (int i = 0; i < Student.size(); i++) {
                Student.get(i).setClassName(userClassMap.get(Student.get(i).getClassId()).getClassName());
            }
            model.addAttribute("Student",Student);
            List<User> parents = userService.findUserByAuthority("4");
            model.addAttribute("parents",parents);
        }else {
            List<User> teach = userService.findUserByAuthority("2");
            for (int i = 0; i < teach.size(); i++) {
                teach.get(i).setClassName(userClassMap.get(teach.get(i).getClassId()).getClassName());
            }
            model.addAttribute("teach",teach);
        }
        return result;
    }
}
