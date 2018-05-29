package com.vs.dao.impl;

import com.vs.dao.UserDao;
import com.vs.entity.User;
import com.vs.entity.UserClass;
import com.vs.mapper.UserClassMapper;
import com.vs.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("empDao")
public class UserDaoImpi implements UserDao {

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
}
