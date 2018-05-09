package com.vs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vs.dao.EmpDao;
import com.vs.entity.Emp;
import com.vs.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "empService")
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDao empDao;

    @Override
    public int addEmp(Emp emp) {
        return empDao.insertSelective(emp);
    }

    @Override
    public PageInfo<Emp> findAllEmp(int pageNum, int pageSize) {
        //使用分页插件
        long startTime = System.currentTimeMillis();
        PageHelper.startPage(pageNum,pageSize);
        List<Emp> empList = empDao.selectEmpInfoLIst();
        PageInfo<Emp> empPageInfo = new PageInfo<>(empList);

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        return empPageInfo;
    }
}
