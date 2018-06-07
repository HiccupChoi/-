package com.vs.service.impl;

import com.vs.dao.ClassDao;
import com.vs.entity.UserClass;
import com.vs.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassDao classDao;

    @Override
    public List<UserClass> findAllClass() {
        return classDao.findAllClass();
    }
}
