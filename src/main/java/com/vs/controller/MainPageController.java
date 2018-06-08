package com.vs.controller;

import com.vs.entity.*;
import com.vs.enums.ChartEnum;
import com.vs.result.Result;
import com.vs.result.ResultList;
import com.vs.result.ResultMap;
import com.vs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainPageController {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ExamService examService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    @Autowired
    private ScoreController scoreController;

    @Autowired
    private ClassService classService;

    private Map<Integer,User> userMap = new HashMap<>();
    private Map<Integer,Subject> subjectMap = new HashMap<>();
    private Map<Integer,Exam> examMap = new HashMap<>();
    private Map<Integer,Score> scoreMap = new HashMap<>();
    private Map<Integer,UserClass> userClassMap = new HashMap<>();
    /**
     * 学生/家长 进入个人首页
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/user")
    public String toEssay(Model model, HttpServletRequest request){
        User user = new User();
        Score score = new Score();
        List<User> userList = new ArrayList<>();
        if (request.getSession().getAttribute("user") != null){
            user = (User)request.getSession().getAttribute("user");
        } else {
            return "redirect:/";
        }

        model.addAttribute("user",user);
        if(!user.getAuthority().equals("3")){
            if(user.getAuthority().equals("4")){
                String studentCode = "1" + user.getUserCode().substring(1);
                user = userService.findUserByCode(studentCode);
            }

            //注入课程和考试信息
            List<Subject> subjectList = subjectService.findSubjectAll();
            List<Exam> examList = examService.findExamAll();
            model.addAttribute("subjectList",subjectList);
            model.addAttribute("examList",examList);

            //权限为2,则将所有学生信息注入
            //将学生信息以key为id转为map,方便使用
            if (user.getAuthority().equals("2")){
                userList = userService.findStudentByClass(user.getClassId());
                for (User userInfo:userList) {
                    userMap.put(userInfo.getUserId(),userInfo);
                }
                model.addAttribute("studentList",userList);

                //获取班内学生第一场考试所有成绩
                String examId = (examList.size() > 0 ? examList.get(0).getExamId() : 100) + "";
                String lastExamId = (examList.size() > 0 ? examList.get(examList.size()-1).getExamId() : 100) + "";
                ResultList examAllScoreResultList =scoreController.findAllScoreByExam(examId,request);
                ResultList examAllScoreResultLastList =scoreController.findAllScoreByExam(lastExamId,request);

                int index = 0;
                for (int i = 0; i < userList.size(); i++) {
                    ResultList result = scoreController.findScoreByExamAndStudent(lastExamId,(userList.get(i).getUserId()+""),request);
                    if (examAllScoreResultLastList.getStringList().contains(result.getUsername())){
                        result.getMapList().add(new ResultMap(
                                examAllScoreResultLastList.getIntegerList().get(index),
                                "总分"
                        ));
                        index++;
                    }
                    examAllScoreResultList.getListmap().put(userList.get(i),result.getMapList());
                }
                model.addAttribute("examAllScoreResultList",examAllScoreResultList);
            }

            //获取总成绩
            score = new Score();
            score.setOnwerId(user.getUserId());
            Result totalScoreResult = scoreService.FindScore(score);
            ResultList totalResultList = toResultList(totalScoreResult,"总成绩变化图",ChartEnum.LINECHART.getType());
            model.addAttribute("totalResultList",totalResultList);

            //获取科目成绩
            score = new Score();
            if (user.getAuthority().equals("2")){
                score.setOnwerId(userList.get(0).getUserId());
            } else {
                score.setOnwerId(user.getUserId());
            }
            score.setSubjectId(subjectList.get(0).getSubjectId());
            Result subjectScoreResult = scoreService.FindScore(score);
            ResultList subjectResultList = toResultList(subjectScoreResult,"各科目成绩",ChartEnum.LINECHART.getType());
            model.addAttribute("subjectResultList",subjectResultList);

            //获取考试中某一场的详细科目成绩
            score = new Score();
            String userName = "";
            if (user.getAuthority().equals("2")){
                userName = userList.get(0).getUserName();
                score.setOnwerId(userList.get(0).getUserId());
            } else {
                score.setOnwerId(user.getUserId());
            }
            score.setExamId(examList.get(0).getExamId());
            Result examScoreResult = scoreService.FindScore(score);
            ResultList examResultList = toResultList(examScoreResult,"考试成绩详情",ChartEnum.ROSECHART.getType());
            examResultList.setUsername(userName);
            model.addAttribute("examResultList",examResultList);
        } else {
            List<UserClass> userClasses = classService.findAllClass();
            for (int i = 0; i < userClasses.size(); i++) {
                userClassMap.put(userClasses.get(i).getClassId(),userClasses.get(i));
            }
            model.addAttribute("userClasses",userClasses);
            List<User> Student = userService.findUserByAuthority("1");
            for (int i = 0; i < Student.size(); i++) {
                Student.get(i).setClassName(userClassMap.get(Student.get(i).getClassId()).getClassName());
            }
            model.addAttribute("Student",Student);
            List<User> teach = userService.findUserByAuthority("2");
            for (int i = 0; i < teach.size(); i++) {
                teach.get(i).setClassName(userClassMap.get(teach.get(i).getClassId()).getClassName());
            }
            model.addAttribute("teach",teach);
            List<User> preants = userService.findUserByAuthority("4");
            model.addAttribute("preants",preants);

            List<User> InvalidStudent =  userService.findInvalidUser("1");
            for (int i = 0; i < InvalidStudent.size(); i++) {
                InvalidStudent.get(i).setClassName(userClassMap.get(InvalidStudent.get(i).getClassId()).getClassName());
            }
            model.addAttribute("InvalidStudent",InvalidStudent);

            List<User> InvalidTeacher =  userService.findInvalidUser("2");
            for (int i = 0; i < InvalidTeacher.size(); i++) {
                InvalidTeacher.get(i).setClassName(userClassMap.get(InvalidTeacher.get(i).getClassId()).getClassName());
            }
            model.addAttribute("InvalidTeacher",InvalidTeacher);

            List<User> InvalidParents =  userService.findInvalidUser("4");
            model.addAttribute("InvalidParents",InvalidParents);
        }


        if (user.getAuthority().equals("2")){
            return "teacher";
        } else if (user.getAuthority().equals("3")){
            return "manager";
        } else {
            return "user";
        }

    }

    //翻译
    public ResultList toResultList(Result result, String title, int type){
        ResultList resultList = new ResultList();
        List<Subject> subjectList = subjectService.findSubjectAll();
        subjectMap = new HashMap<>();
        for (Subject subject:subjectList) {
            subjectMap.put(subject.getSubjectId(),subject);
        }
        List<Exam> examList = examService.findExamAll();
        examMap = new HashMap<>();
        for (Exam exam:examList) {
            examMap.put(exam.getExamId(),exam);
        }
        List<Score> totalScoreList = (List<Score>) result.getData();
        scoreMap = new HashMap<>();
        if (result.isSuccess()){
            for (Score score : totalScoreList) {
                scoreMap.put(score.getSubjectId(),score);
            }
        }

        //添加标题
        resultList.setTitle(title);

        //添加score
        List<Integer> integers = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        int min = 0;
        if (totalScoreList != null && totalScoreList.size()>0 && type != ChartEnum.LINECHARWITHZOOM.getType()){
            min = totalScoreList.get(0).getScore();
            for (Score scores: totalScoreList) {
                integers.add(scores.getScore());
                if (type == ChartEnum.LINECHART.getType()){
                    strings.add(examMap.get(scores.getExamId()).getExamName());
                }
                if (type == ChartEnum.ROSECHART.getType()){
                    strings.add(subjectMap.get(scores.getSubjectId()).getSubjectName());
                }
                if (min > scores.getScore()){
                    min = scores.getScore();
                }
            }
        }
        resultList.setIntegerList(integers);
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
        if (type == ChartEnum.ROSECHART.getType() || type == ChartEnum.LINECHARWITHZOOM.getType()){
            List<ResultMap> resultMapList = new ArrayList<>();
            int sumScore = 0;
            for (int i = 0; i < subjectList.size(); i++) {
                int score = 0;

                if (totalScoreList != null && totalScoreList.size() > 0){
                    if (i < totalScoreList.size() && (totalScoreList.get(i).getSubjectId() == 10)){
                        continue;
                    }
                    score = scoreMap.get(subjectList.get(i).getSubjectId()) != null ? scoreMap.get(subjectList.get(i).getSubjectId()).getScore() : 0 ;
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
