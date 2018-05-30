package com.vs.service;


import com.vs.entity.User;
import com.vs.result.Result;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public Result login(User user);

    public Result checkThreeCode(String userCode,String UserName,String activationCode);

    public Result register(String userCode,String userName,String UserPwd);
}