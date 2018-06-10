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
    private Map<Integer,Subject> subjectMap = new HashMap<>();
    private Map<Integer,Exam> examMap = new HashMap<>();
    private Map<Integer,Score> scoreMap = new HashMap<>();

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


    /**
     * 教师用  通过考试和学生id查询详情
     * @param selectValue
     * @param studentId
     * @param request
     * @return
     */
    @RequestMapping("/findScoreByExamAndStudent")
    public ResultList findScoreByExamAndStudent(String selectValue,String studentId, HttpServletRequest request){
        Score score = new Score();
        User user = new User();
        String username ="";
        if (request.getSession().getAttribute("user")!=null){
            user = (User)request.getSession().getAttribute("user");
        }
        if(user.getAuthority().equals("4")){
            String studentCode = "1" + user.getUserCode().substring(1);
            user = userService.findUserByCode(studentCode);
        }
        if (studentId != null && !studentId.isEmpty()){
            score.setOnwerId(Integer.parseInt(studentId));
            username = userMap.get(Integer.parseInt(studentId)).getUserName();
        }else {
            score.setOnwerId(user.getUserId());
            username = userMap.get(user.getUserId()).getUserName();
        }
        score.setExamId(Integer.parseInt(selectValue));

        Result result = scoreService.FindScore(score);
        ResultList resultList = toResultList(result,"考试成绩详情",ChartEnum.ROSECHART.getType());
        resultList.setUsername(username);
        return resultList;
    }

    /**
     * 学生用ajax查询每一场考试中的各科目详细成绩
     * @param selectValue
     * @param request
     * @return
     */
    @RequestMapping("/findScoreByExam")
    public ResultList findScoreByExam(String selectValue, HttpServletRequest request){
        Score score = new Score();
        User user = new User();
        String username ="";
        if (request.getSession().getAttribute("user")!=null){
            user = (User)request.getSession().getAttribute("user");
        }
        if(user.getAuthority().equals("4")){
            String studentCode = "1" + user.getUserCode().substring(1);
            user = userService.findUserByCode(studentCode);
        }
        score.setOnwerId(user.getUserId());
        username = userService.findUserByCode(user.getUserCode()).getUserName();
        score.setExamId(Integer.parseInt(selectValue));

        Result result = scoreService.FindScore(score);
        ResultList resultList = toResultList(result,"考试成绩详情",ChartEnum.ROSECHART.getType());
        resultList.setUsername(username);
        return resultList;
    }


    @RequestMapping("/findScoreByStudentAndExam")
    public ResultList findScoreByStudentAndExam(String selectValue,String examId, HttpServletRequest request){
        Score score = new Score();
        User user = new User();
        String username ="";
        if (request.getSession().getAttribute("user")!=null){
            user = (User)request.getSession().getAttribute("user");
        }
        if(user.getAuthority().equals("4")){
            String studentCode = "1" + user.getUserCode().substring(1);
            user = userService.findUserByCode(studentCode);
        }
        if (examId != null && !examId.isEmpty()){
            score.setExamId(Integer.parseInt(examId));
            username = userMap.get(Integer.parseInt(selectValue)).getUserName();
        }
        score.setOnwerId(Integer.parseInt(selectValue));

        Result result = scoreService.FindScore(score);
        ResultList resultList = toResultList(result,"考试成绩详情",ChartEnum.ROSECHART.getType());
        resultList.setUsername(username);
        return resultList;
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
            int index = 0;
            for (int i = 0; i < subjectList.size(); i++) {
                int score = 0;

                if (totalScoreList != null && totalScoreList.size() > 0){
                    if (scoreMap.get(subjectList.get(i).getSubjectId()) != null && totalScoreList.get(index).getSubjectId() == 10){
                        continue;
                    }
                    score = scoreMap.get(subjectList.get(i).getSubjectId()) != null ? scoreMap.get(subjectList.get(i).getSubjectId()).getScore() : 0 ;
                    if (scoreMap.get(subjectList.get(i).getSubjectId()) != null){
                        index++;
                    }
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



    @RequestMapping("/addScore")
    public Result addScore(String math,String chinese,String english,String physical,String chemistry,String biology,String geography,String history,String politics,HttpServletRequest request){
        Integer[] integerArray = {
                //TODO 保证顺序和数据库顺序相同
            math.isEmpty() ? null : Integer.parseInt(math),
            chinese.isEmpty() ? null : Integer.parseInt(chinese),
            english.isEmpty() ? null : Integer.parseInt(english),
            physical.isEmpty() ? null : Integer.parseInt(physical),
            chemistry.isEmpty() ? null : Integer.parseInt(chemistry),
            biology.isEmpty() ? null : Integer.parseInt(biology),
            geography.isEmpty() ? null : Integer.parseInt(geography),
            history.isEmpty() ? null : Integer.parseInt(history),
            politics.isEmpty() ? null : Integer.parseInt(politics)
        };

        List<Subject> subjectList = subjectService.findSubjectAll();
        List<Exam> examList = examService.findExamAll();

        Score score = new Score();
        for (int i = 0; i < subjectList.size() - 1; i++) {
            if (integerArray[i] == null){
                continue;
            }
            score = new Score();
            score.setExamId(examList.get(examList.size() - 1).getExamId());
            score.setOnwerId(Integer.parseInt((String) request.getSession().getAttribute("StudentId")));
            score.setSubjectId(subjectList.get(i).getSubjectId());
            score.setScore(integerArray[i]);
            scoreService.AddScore(score);
        }

        score = new Score();
        int sumScore = 0;
        score.setExamId(examList.get(examList.size() - 1).getExamId());
        score.setOnwerId(Integer.parseInt((String) request.getSession().getAttribute("StudentId")));
        List<Score> scores = (List<Score>)scoreService.FindScore(score).getData();
        for (Score scoreA:scores) {
            if (scoreA.getSubjectId() != 10){
                sumScore += scoreA.getScore();
            }
        }
        score.setSubjectId(subjectList.get(subjectList.size()-1).getSubjectId());
        score.setScore(sumScore);
        scoreService.AddScore(score);

        Result result = new Result();
        result.setSuccess(true);

        return result;
    }

    @RequestMapping("/addUserIdToSession")
    private Result addUserIdToSession(String StudentId,HttpServletRequest request){
        request.getSession().setAttribute("StudentId",StudentId);
        return null;
    }


    //添加分数工具(随机)
    @RequestMapping("/addScoreFalse")
    public void addScoreFalse(){
        Score score = new Score();
        for (int i = 1; i < 9; i++) {
            int sum = 0;
            for (int j = 1; j < 10; j++) {
                score = new Score();
                score.setExamId(i);
                score.setSubjectId(j);
                int scoreInt = 0;
                while (true){
                    scoreInt = (int)(Math.random()*99) + 1 ;
                    if (scoreInt > 60) break;
                }
                sum += scoreInt;
                score.setScore(scoreInt);
                score.setOnwerId(13);
                scoreService.AddScore(score);
            }
            score.setScore(sum);
            score.setSubjectId(10);
            scoreService.AddScore(score);
        }
    }
}

