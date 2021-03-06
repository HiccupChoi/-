package com.vs.mapper;

import com.vs.entity.Exam;

import java.util.List;

public interface ExamMapper {
    int deleteByPrimaryKey(Integer examId);

    int insert(Exam record);

    int insertSelective(Exam record);

    Exam selectByPrimaryKey(Integer examId);

    int updateByPrimaryKeySelective(Exam record);

    int updateByPrimaryKey(Exam record);

    List<Exam> selectExamAll();
}