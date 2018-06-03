package com.vs.dao;

import com.vs.entity.Subject;

import java.util.List;

public interface SubjectDao {

    int deleteByPrimaryKey(Integer subjectId);

    int insert(Subject record);

    int insertSelective(Subject record);

    Subject selectByPrimaryKey(Integer subjectId);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);

    List<Subject> findSubjectAll();

}
