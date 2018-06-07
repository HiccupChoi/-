package com.vs.service.impl;

import com.vs.dao.ScoreDao;
import com.vs.dao.UserDao;
import com.vs.entity.Score;
import com.vs.entity.User;
import com.vs.result.Result;
import com.vs.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreDao scoreDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Result FindScore(Score score) {
        List<Score> scores = new ArrayList<>();
        if (score.getOnwerId() == null){
            scores = findScoreAllNull(score);
        } else if (score.getSubjectId() == null && score.getExamId() == null) {
            score.setSubjectId(10);
            scores.addAll(scoreDao.FindScore(score));
        } else {
            scores = scoreDao.FindScore(score);
        }
        Result result = new Result();
        if (scores.size() > 0 ){
            result.setStatus(0);
            result.setSuccess(true);
            result.setMsg("查询成功");
            result.setData(scores);
        }
        return result;
    }

    /**
     * 学生id为空时进行的查询
     * @param score
     * @return
     */
    public List<Score> findScoreAllNull(Score score){
        List<Score> scores = new ArrayList<>();

        List<User> users = userDao.findStudentsByClassId(1417);
        for (User user: users) {
            score.setOnwerId(user.getUserId());
            score.setSubjectId(10);
            scores.addAll(scoreDao.FindScore(score));
        }

        return scores;
    }

    /**
     * 添加成绩
     * @return
     */
    @Override
    public int AddScore(Score score) {
        List<Score> scores = scoreDao.FindScore(score);
        if (scores.size() > 0){
            score.setScoreId(scores.get(0).getScoreId());
            return scoreDao.updateByPrimaryKeySelective(score);
        }
        return scoreDao.insertSelective(score);
    }

}
