package com.vs.result;

import com.vs.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用作返回成绩
 */
public class ResultList {
    private List<String> stringList;
    private List<Integer> integerList;
    private List<ResultMap> mapList;
    private Map<User,List<ResultMap>> Listmap = new HashMap<>();
    private String title;
    private String username;
    private Integer min;
    private Integer sunScore;

    public Map<User, List<ResultMap>> getListmap() {
        return Listmap;
    }

    public void setListmap(Map<User, List<ResultMap>> listmap) {
        Listmap = listmap;
    }

    public Integer getSunScore() {
        return sunScore;
    }

    public void setSunScore(Integer sunScore) {
        this.sunScore = sunScore;
    }

    public List<ResultMap> getMapList() {
        return mapList;
    }

    public void setMapList(List<ResultMap> mapList) {
        this.mapList = mapList;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public void setIntegerList(List<Integer> integerList) {
        this.integerList = integerList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }
}
