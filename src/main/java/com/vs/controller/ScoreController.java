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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Map<Integer,User> userMap = new HashMap<>();

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

    @RequestMapping("findAllScoreByExam")
    public ResultList findAllScoreByExam(String selectValue, HttpServletRequest request){
        Score score = new Score();
        User user = new User();
        if (request.getSession().getAttribute("user")!=null){
            user = (User)request.getSession().getAttribute("user");
        }
        List<User> userList = userService.findStudentByClass(user.getClassId());
        for (User userInfo:userList) {
            userMap.put(userInfo.getUserId(),userInfo);
        }

        score.setExamId(Integer.parseInt(selectValue));
        score.setSubjectId(10);

        Result examAllScoreResult = scoreService.FindScore(score);
        return toResultList(examAllScoreResult,"班内学生成绩一览",ChartEnum.LINECHARWITHZOOM.getType());
    }


    @RequestMapping("/findScoreByExam")
    public ResultList findScoreByExam(String selectValue,String studentId, HttpServletRequest request){
        Score score = new Score();
        User user = new User();
        if (request.getSession().getAttribute("user")!=null){
            user = (User)request.getSession().getAttribute("user");
        }
        if(user.getAuthority().equals("4")){
            String studentCode = "1" + user.getUserCode().substring(1);
            user = userService.findUserByCode(studentCode);
        }
        if (studentId != null && !studentId.isEmpty()){
            score.setOnwerId(Integer.parseInt(studentId));
        }else {
            score.setOnwerId(user.getUserId());
        }
        score.setExamId(Integer.parseInt(selectValue));

        Result result = scoreService.FindScore(score);

        return toResultList(result,"考试成绩详情",ChartEnum.ROSECHART.getType());
    }

    //翻译
    public ResultList toResultList(Result result, String title, int type){
        ResultList resultList = new ResultList();
        List<Subject> subjectList = subjectService.findSubjectAll();
        List<Exam> examList = examService.findExamAll();

        //添加标题
        resultList.setTitle(title);

        //添加score
        List<Score> totalScoreList = (List<Score>) result.getData();
        List<Integer> integers = new ArrayList<>();
        int min = 0;
        if (totalScoreList != null && totalScoreList.size()>0 && type != ChartEnum.LINECHARWITHZOOM.getType()){
            min = totalScoreList.get(0).getScore();
            for (Score scores: totalScoreList) {
                integers.add(scores.getScore());
                if (min > scores.getScore()){
                    min = scores.getScore();
                }
            }
        }
        resultList.setIntegerList(integers);

        //添加分组
        List<String> strings = new ArrayList<>();
        if (type == ChartEnum.LINECHART.getType()){
            for (Exam exam : examList) {
                strings.add(exam.getExamName());
            }
        }
        if (type == ChartEnum.ROSECHART.getType()){
            for (Subject subject : subjectList) {
                strings.add(subject.getSubjectName());
            }
        }
        resultList.setStringList(strings);
        if (type == ChartEnum.LINECHARWITHZOOM.getType()){
            if (totalScoreList != null && totalScoreList.size()>0){
                min = totalScoreList.get(0).getScore();
                for (Score scores: totalScoreList) {
                    integers.add(scores.getScore());
                    strings.add(userMap.get(scores.getOnwerId()).getUserName());
                    if (min > scores.getScore()){
                        min = scores.getScore();
                    }
                }
                resultList.setStringList(strings);
                resultList.setIntegerList(integers);
            }
        }

        //添加min
        min =  (min > 100) ? (min - 50) : (min - 10);
        resultList.setMin(min);


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
