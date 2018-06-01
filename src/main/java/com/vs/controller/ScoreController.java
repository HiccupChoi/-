package com.vs.controller;

import com.vs.entity.Score;
import com.vs.result.Result;
import com.vs.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @RequestMapping("/findScore")
    public Result FindScore(){
        Score score = new Score();
        score.setOnwerId(1);
        score.setSubjectId(1);
        return scoreService.FindScore(score);
    }

}
