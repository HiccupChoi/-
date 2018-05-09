package com.vs.mapper;

import com.vs.entity.Emp;

import java.util.List;

public interface EmpMapper {
    int deleteByPrimaryKey(Integer empId);

    int insert(Emp record);

    int insertSelective(Emp record);

    Emp selectByPrimaryKey(Integer empId);

    int updateByPrimaryKeySelective(Emp record);

    int updateByPrimaryKey(Emp record);

    List<Emp> selectEmpInfoLIst();
}