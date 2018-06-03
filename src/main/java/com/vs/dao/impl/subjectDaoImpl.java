package com.vs.dao.impl;

import com.vs.dao.SubjectDao;
import com.vs.entity.Subject;
import com.vs.mapper.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class subjectDaoImpl implements SubjectDao {

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public int deleteByPrimaryKey(Integer subjectId) {
        return subjectMapper.deleteByPrimaryKey(subjectId);
    }

    @Override
    public int insert(Subject record) {
        return subjectMapper.insert(record);
    }

    @Override
    public int insertSelective(Subject record) {
        return subjectMapper.insertSelective(record);
    }

    @Override
    public Subject selectByPrimaryKey(Integer subjectId) {
        return subjectMapper.selectByPrimaryKey(subjectId);
    }

    @Override
    public int updateByPrimaryKeySelective(Subject record) {
        return subjectMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Subject record) {
        return subjectMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Subject> findSubjectAll() {
        return subjectMapper.findSubjectAll();
    }
}
