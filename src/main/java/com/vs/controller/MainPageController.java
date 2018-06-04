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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 学生/家长 进入个人首页
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/user")
    public String toEssay(Model model, HttpServletRequest request){
        User user = new User();

        if (request.getSession().getAttribute("user") != null){
            user = (User)request.getSession().getAttribute("user");
        } else {
            return "redirect:/";
        }

        model.addAttribute("user",user);

        if(user.getAuthority().equals("4")){
            String studentCode = "1" + user.getUserCode().substring(1);
            user = userService.findUserByCode(studentCode);
        }

        List<Subject> subjectList = subjectService.findSubjectAll();
        List<Exam> examList = examService.findExamAll();
        model.addAttribute("subjectList",subjectList);
        model.addAttribute("examList",examList);

        //获取总成绩
        Score score = new Score();
        score.setOnwerId(user.getUserId());
        Result totalScoreResult = scoreService.FindScore(score);
        ResultList totalResultList = toResultList(totalScoreResult,"总成绩变化图",ChartEnum.LINECHART.getType());
        model.addAttribute("totalResultList",totalResultList);

        //获取科目成绩
        score = new Score();
        score.setOnwerId(user.getUserId());
        score.setSubjectId(subjectList.get(0).getSubjectId());
        Result subjectScoreResult = scoreService.FindScore(score);
        ResultList subjectResultList = toResultList(subjectScoreResult,"各科目成绩",ChartEnum.LINECHART.getType());
        model.addAttribute("subjectResultList",subjectResultList);

        //获取考试中某一场的详细科目成绩
        score = new Score();
        score.setOnwerId(user.getUserId());
        score.setExamId(examList.get(0).getExamId());
        Result examScoreResult = scoreService.FindScore(score);
        ResultList examResultList = toResultList(examScoreResult,"考试成绩详情",ChartEnum.ROSECHART.getType());
        model.addAttribute("examResultList",examResultList);

        return "user";
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
            for (int i = 0; i < subjectList.size() - 1; i++) {

                if (i < totalScoreList.size() && (totalScoreList.get(i).getSubjectId() == 10)){
                    continue;
                }

                int score = i < totalScoreList.size() ? totalScoreList.get(i).getScore() : 0 ;
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
