package com.vs.dao.impl;

import com.vs.dao.ScoreDao;
import com.vs.entity.Score;
import com.vs.mapper.ScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScoreDaoImpl implements ScoreDao {

    @Autowired
    private ScoreMapper scoreMapper;


    @Override
    public int deleteByPrimaryKey(Integer scoreId) {
        return scoreMapper.deleteByPrimaryKey(scoreId);
    }

    @Override
    public int insert(Score record) {
        return scoreMapper.insert(record);
    }

    @Override
    public int insertSelective(Score record) {
        return scoreMapper.insertSelective(record);
    }

    @Override
    public Score selectByPrimaryKey(Integer scoreId) {
        return scoreMapper.selectByPrimaryKey(scoreId);
    }

    @Override
    public int updateByPrimaryKeySelective(Score record) {
        return scoreMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Score record) {
        return scoreMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Score> FindScore(Score score) {
        return scoreMapper.selectScoreByScore(score);
    }
}
