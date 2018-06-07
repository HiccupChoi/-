package com.vs.dao.impl;

import com.vs.dao.ClassDao;
import com.vs.entity.UserClass;
import com.vs.mapper.UserClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClassDaoImpl implements ClassDao {

    @Autowired
    private UserClassMapper userClassMapper;

    @Override
    public List<UserClass> findAllClass() {
        return userClassMapper.selectClassAll();
    }
}
