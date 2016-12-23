package com.zhanghao.skinexpert.beans;

import java.util.List;

/**
 * Created by RockGao on 2016/12/23.
 */

public class TotalQuestionBean {
    private int id ;
    private String title;
    private int count;
    private List<TestResultBean> results;
    private List<List<Object>>  questions;

    public TotalQuestionBean() {
    }

    public TotalQuestionBean(int id, String title, int count, List<TestResultBean> results, List<List<Object>>  questions) {
        this.id = id;
        this.title = title;
        this.count = count;
        this.results = results;
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<TestResultBean> getResults() {
        return results;
    }

    public void setResults(List<TestResultBean> results) {
        this.results = results;
    }

    public List<List<Object>>  getQuestions() {
        return questions;
    }

    public void setQuestions(List<List<Object>> questions) {
        this.questions = questions;
    }
}
