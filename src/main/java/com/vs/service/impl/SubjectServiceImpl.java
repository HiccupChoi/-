package com.vs.service.impl;

import com.vs.dao.SubjectDao;
import com.vs.entity.Subject;
import com.vs.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    @Override
    public List<Subject> findSubjectAll() {
        return subjectDao.findSubjectAll();
    }
}
