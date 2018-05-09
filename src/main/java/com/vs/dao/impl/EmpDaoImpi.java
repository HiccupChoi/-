package com.vs.dao.impl;

import com.vs.dao.EmpDao;
import com.vs.entity.Emp;
import com.vs.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("empDaoImpi")
public class EmpDaoImpi implements EmpDao {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public int deleteByPrimaryKey(Integer empId) {
        return empMapper.deleteByPrimaryKey(empId);
    }

    @Override
    public int insert(Emp record) {
        return empMapper.insert(record);
    }

    @Override
    public int insertSelective(Emp record) {
        return empMapper.insertSelective(record);
    }

    @Override
    public Emp selectByPrimaryKey(Integer empId) {
        return empMapper.selectByPrimaryKey(empId);
    }

    @Override
    public int updateByPrimaryKeySelective(Emp record) {
        return empMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Emp record) {
        return empMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Emp> selectEmpInfoLIst() {
        return empMapper.selectEmpInfoLIst();
    }
}
