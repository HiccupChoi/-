package com.vs.service;


import com.vs.entity.User;
import com.vs.result.Result;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Result login(User user);

    Result checkThreeCode(String userCode,String UserName,String activationCode);

    Result register(String userCode,String userName,String UserPwd);

    Result createUser(User user);

    Result cancelUser(String userCode);
}