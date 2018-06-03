package com.vs.service.impl;

import com.vs.dao.ExamDao;
import com.vs.entity.Exam;
import com.vs.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamDao examDao;

    @Override
    public List<Exam> findExamAll() {
        return examDao.selectExamAll();
    }
}
