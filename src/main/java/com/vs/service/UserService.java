package com.vs.service;


import com.vs.entity.User;
import com.vs.result.Result;

import java.util.List;

public interface UserService {
    Result login(User user);

    Result checkThreeCode(String userCode,String UserName,String activationCode);

    Result register(String userCode,String userName,String UserPwd);

    Result createUser(User user);

    Result cancelUser(String userCode);

    User findUserByCode(String code);

    List<User> findStudentByClass(Integer classId);

    List<User> findUserByAuthority(String authority);
}