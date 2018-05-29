package com.vs.service.impl;

import com.vs.dao.UserDao;
import com.vs.entity.User;
import com.vs.entity.UserClass;
import com.vs.result.Result;
import com.vs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 欢
 */
@Service(value = "empService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 查询登录用户信息
     * @param user
     * @return
     */
    public Result login(User user){
        User userInfo = userDao.login(user);
        Result result = new Result();
        if (userInfo != null){
            result.setStatus(0);
            result.setMsg("登录成功");
            result.setSuccess(true);
            UserClass userClass = userDao.findClass(userInfo.getClassId());
            User teach = userDao.findTeachByClassAndQ(user.getClassId());
            if (userClass != null){
                userInfo.setClassName(userClass.getClassName());
            }
            if (teach != null){
                userInfo.setTeachName(teach.getUserName());
            }
            result.setData(userInfo);
        } else {
            result.setStatus(1);
            if (userDao.findUserByCode(user.getUserCode()) > 0){
                result.setMsg("密码错误");
            } else {
                result.setMsg("该用户不存在或已被删除");
            }
            result.setSuccess(false);
            result.setData(null);
        }
        return result;
    }

    /**
     * 检测三码合一
     * @param UserNo
     * @param UserName
     * @param UserCode
     * @return
     */
    public boolean checkThreeCode(String UserNo,String UserName,String UserCode){
        return userDao.checkThreeCode(UserNo,UserName,UserCode) > 0;
    }

}
