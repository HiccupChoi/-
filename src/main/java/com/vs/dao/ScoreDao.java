package com.vs.dao;

import com.vs.entity.Score;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ScoreDao {

    /**
     * 删除成绩
     * @param scoreId
     * @return
     */
    int deleteByPrimaryKey(Integer scoreId);

    /**
     * 插入成绩
     * @param record
     * @return
     */
    int insert(Score record);

    /**
     * 有则插入
     * @param record
     * @return
     */
    int insertSelective(Score record);

    /**
     * 根据主键查询成绩
     * @param scoreId
     * @return
     */
    Score selectByPrimaryKey(Integer scoreId);

    /**
     * 根据主键更新成绩
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Score record);

    /**
     * 根据主键更新成绩
     * @param record
     * @return
     */
    int updateByPrimaryKey(Score record);

    /**
     * 更具查询条件查询符合条件的列表
     * @param score
     * @return
     */
    List<Score> FindScore(Score score);
}
