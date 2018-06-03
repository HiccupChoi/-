package com.vs.controller;

import com.vs.entity.Exam;
import com.vs.entity.Score;
import com.vs.entity.Subject;
import com.vs.entity.User;
import com.vs.result.Result;
import com.vs.result.ResultList;
import com.vs.service.ExamService;
import com.vs.service.ScoreService;
import com.vs.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
public class MainPageController {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ExamService examService;

    @Autowired
    private SubjectService subjectService;

    /**
     * 进入个人首页
     * @param model
     * @return
     */
    @RequestMapping(value = "/user")
    public String toEssay(Model model, HttpServletRequest request){
        User user = new User();

        if (request.getSession().getAttribute("user") != null){
            user = (User)request.getSession().getAttribute("user");
        }
        model.addAttribute("user",user);

        List<Subject> subjectList = subjectService.findSubjectAll();
        model.addAttribute("subjectList",subjectList);

        //获取总成绩
        Score score = new Score();
        score.setOnwerId(user.getUserId());
        Result totalScoreResult = scoreService.FindScore(score);
        ResultList totalResultList = toResultList(totalScoreResult,"总成绩变化图");
        model.addAttribute("totalResultList",totalResultList);

        //获取科目成绩
        score = new Score();
        score.setOnwerId(user.getUserId());
        score.setSubjectId(subjectList.get(0).getSubjectId());
        Result subjectScoreResult = scoreService.FindScore(score);
        ResultList subjectResultList = toResultList(subjectScoreResult,"各科目成绩");
        model.addAttribute("subjectResultList",subjectResultList);

        return "user";
    }

    public ResultList toResultList(Result result,String title){
        ResultList resultList = new ResultList();

        resultList.setTitle(title);
        List<Exam> examList = examService.findExamAll();
        List<Score> totalScoreList = (List<Score>) result.getData();
        List<Integer> integers = new ArrayList<>();
        int min = totalScoreList.get(0).getScore();
        for (Score scores: totalScoreList) {
            integers.add(scores.getScore());
            if (min > scores.getScore()){
                min = scores.getScore();
            }
        }
        min =  (min > 100) ? (min - 50) : (min - 10);
        resultList.setIntegerList(integers);
        resultList.setMin(min);
        List<String> strings = new ArrayList<>();
        for (Exam exam : examList) {
            strings.add(exam.getExamName());
        }
        resultList.setStringList(strings);

        return resultList;
    }

}
