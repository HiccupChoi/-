package com.vs.dao;

import com.vs.entity.Emp;

import java.util.List;

public interface EmpDao {
    /**
     * 通过empId删除员工
     * @param empId
     * @return
     */
    int deleteByPrimaryKey(Integer empId);

    /**
     * 插入员工(员工信息不为null)
     * @param record
     * @return
     */
    int insert(Emp record);

    /**
     * 插入员工(员工信息可以为null.mysql动态拼接)
     * @param record
     * @return
     */
    int insertSelective(Emp record);

    /**
     * 通过empid查询员工信息
     * @param empId
     * @return
     */
    Emp selectByPrimaryKey(Integer empId);

    /**
     * 修改员工信息(部分信息)
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Emp record);

    /**
     * 修改员工信息(全部信息)
     * @param record
     * @return
     */
    int updateByPrimaryKey(Emp record);

    /**
     * 查询员工信息
     * @return
     */
    List<Emp> selectEmpInfoLIst();
}
