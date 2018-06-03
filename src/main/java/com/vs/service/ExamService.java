package com.vs.service;

import com.vs.entity.Exam;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ExamService {

    List<Exam> findExamAll();

}
