package com.vs.dao.impl;

import com.vs.dao.UserDao;
import com.vs.entity.User;
import com.vs.entity.UserClass;
import com.vs.mapper.UserClassMapper;
import com.vs.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserClassMapper userClassMapper;

    @Override
    public User login(User student) {
        return userMapper.chickStudent(student);
    }

    @Override
    public UserClass findClass(Integer classId){
        return userClassMapper.selectByPrimaryKey(classId);
    }

    @Override
    public User findTeachByClassAndQ(Integer classId){
        return userMapper.findTeachByClassAndQ(classId);
  }

    @Override
    public int findUserByCode(String userCode){
        return userMapper.findUserByCode(userCode);
    }

    @Override
    public Integer checkThreeCode(User user){
        return userMapper.checkThreeCode(user);
    }

    @Override
    public int insertSelective(User record){
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByUserCode(String userCode){
        return userMapper.selectByUserCode(userCode);
    }

    @Override
    public Integer checkThreeCodeTimes(String userCode){
        return userMapper.checkThreeCodeTimes(userCode);
    }

    @Override
    public void subUserTimesOne(User user){
        userMapper.subUserTimesOne(user);
    }

    @Override
    public int createUser(User user){
        return userMapper.insertSelective(user);
    }

    @Override
    public int cancelUser(Integer userId){
        return userMapper.cancelUser(userId);
    }
}
