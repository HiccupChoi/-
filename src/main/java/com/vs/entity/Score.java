package com.vs.entity;

public class Score {
    private Integer scoreId;

    private Integer subjectId;

    private Integer score;

    private Integer onwerId;

    private Integer examId;

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getOnwerId() {
        return onwerId;
    }

    public void setOnwerId(Integer onwerId) {
        this.onwerId = onwerId;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }
}