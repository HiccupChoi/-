package com.vs.service.impl;

import com.vs.dao.ScoreDao;
import com.vs.entity.Score;
import com.vs.result.Result;
import com.vs.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreDao scoreDao;

    @Override
    public Result FindScore(Score score) {
        List<Score> scores = scoreDao.FindScore(score);
        return null;
    }

}
