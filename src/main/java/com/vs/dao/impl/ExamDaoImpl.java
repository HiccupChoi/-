package com.vs.dao.impl;

import com.vs.dao.ExamDao;
import com.vs.entity.Exam;
import com.vs.mapper.ExamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExamDaoImpl implements ExamDao {

    @Autowired
    private ExamMapper examMapper;

    @Override
    public int deleteByPrimaryKey(Integer examId) {
        return examMapper.deleteByPrimaryKey(examId);
    }

    @Override
    public int insert(Exam record) {
        return examMapper.insert(record);
    }

    @Override
    public int insertSelective(Exam record) {
        return examMapper.insertSelective(record);
    }

    @Override
    public Exam selectByPrimaryKey(Integer examId) {
        return examMapper.selectByPrimaryKey(examId);
    }

    @Override
    public int updateByPrimaryKeySelective(Exam record) {
        return examMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Exam record) {
        return examMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Exam> selectExamAll() {
        return examMapper.selectExamAll();
    }
}
