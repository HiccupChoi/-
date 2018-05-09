package com.vs.service;


import com.github.pagehelper.PageInfo;
import com.vs.entity.Emp;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/8/16.
 */
@Service
public interface EmpService {
    int addEmp(Emp emp);

    PageInfo<Emp> findAllEmp(int pageNum, int pageSize);

}