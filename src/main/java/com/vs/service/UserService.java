package com.vs.service;


import com.vs.entity.User;
import com.vs.result.Result;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public Result login(User user);

    public boolean checkThreeCode(String UserNo,String UserName,String UserCode);
}