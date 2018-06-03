package com.vs.dao;

import com.vs.entity.Exam;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ExamDao {

    int deleteByPrimaryKey(Integer examId);

    int insert(Exam record);

    int insertSelective(Exam record);

    Exam selectByPrimaryKey(Integer examId);

    int updateByPrimaryKeySelective(Exam record);

    int updateByPrimaryKey(Exam record);

    List<Exam> selectExamAll();
}
