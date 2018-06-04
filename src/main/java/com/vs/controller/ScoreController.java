package com.vs.controller;

import com.vs.entity.Exam;
import com.vs.entity.Score;
import com.vs.entity.Subject;
import com.vs.entity.User;
import com.vs.enums.ChartEnum;
import com.vs.result.Result;
import com.vs.result.ResultList;
import com.vs.result.ResultMap;
import com.vs.service.ExamService;
import com.vs.service.ScoreService;
import com.vs.service.SubjectService;
import com.vs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ExamService examService;

    @Autowired
    private UserService userService;

    @RequestMapping("/findScoreBySubject")
    public ResultList findScoreBySubject(String selectValue, HttpServletRequest request){
        Score score = new Score();
        User user = new User();
        if (request.getSession().getAttribute("user")!=null){
            user = (User)request.getSession().getAttribute("user");
        }
        if(user.getAuthority().equals("4")){
            String studentCode = "1" + user.getUserCode().substring(1);
            user = userService.findUserByCode(studentCode);
        }
        score.setOnwerId(user.getUserId());
        score.setSubjectId(Integer.parseInt(selectValue));

        Result result = scoreService.FindScore(score);
        return toResultList(result,"科目成绩",ChartEnum.LINECHART.getType());
    }

    @RequestMapping("/findScoreByExam")
    public ResultList findScoreByExam(String selectValue, HttpServletRequest request){
        Score score = new Score();
        User user = new User();
        if (request.getSession().getAttribute("user")!=null){
            user = (User)request.getSession().getAttribute("user");
        }
        if(user.getAuthority().equals("4")){
            String studentCode = "1" + user.getUserCode().substring(1);
            user = userService.findUserByCode(studentCode);
        }
        score.setOnwerId(user.getUserId());
        score.setExamId(Integer.parseInt(selectValue));

        Result result = scoreService.FindScore(score);
        return toResultList(result,"考试成绩详情",ChartEnum.ROSECHART.getType());
    }

    //翻译方法一
    public ResultList toResultList(Result result, String title, int type){
        ResultList resultList = new ResultList();
        List<Subject> subjectList = subjectService.findSubjectAll();
        List<Exam> examList = examService.findExamAll();

        //添加标题
        resultList.setTitle(title);

        //添加score与min
        List<Score> totalScoreList = (List<Score>) result.getData();
        List<Integer> integers = new ArrayList<>();
        int min = 0;
        if (totalScoreList != null && totalScoreList.size()>0){
            min = totalScoreList.get(0).getScore();
            for (Score scores: totalScoreList) {
                integers.add(scores.getScore());
                if (min > scores.getScore()){
                    min = scores.getScore();
                }
            }
        }

        min =  (min > 100) ? (min - 50) : (min - 10);
        resultList.setIntegerList(integers);
        resultList.setMin(min);

        //添加分组
        List<String> strings = new ArrayList<>();
        if (type == 1){
            for (Exam exam : examList) {
                strings.add(exam.getExamName());
            }
        }
        if (type == 2){
            for (Subject subject : subjectList) {
                strings.add(subject.getSubjectName());
            }
        }
        resultList.setStringList(strings);


        //添加value,name,sumScore
        if (type == 2){
            List<ResultMap> resultMapList = new ArrayList<>();
            int sumScore = 0;
            for (int i = 0; i < subjectList.size(); i++) {
                int score = 0;

                if (totalScoreList != null && totalScoreList.size() > 0){
                    if (i < totalScoreList.size() && (totalScoreList.get(i).getSubjectId() == 10)){
                        continue;
                    }

                    score =i < totalScoreList.size() ? totalScoreList.get(i).getScore() : 0 ;
                }

                sumScore += score;

                ResultMap resultMap = new ResultMap(
                        score,
                        subjectList.get(i).getSubjectName()
                );

                resultMapList.add(resultMap);
            }
            resultList.setMapList(resultMapList);
            resultList.setSunScore(sumScore);
        }

        return resultList;
    }

}
