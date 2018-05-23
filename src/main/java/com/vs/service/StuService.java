package com.vs.service;


import com.vs.entity.Student;
import com.vs.result.Result;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/8/16.
 */
@Service
public interface StuService {
    public Result login(Student student);
}