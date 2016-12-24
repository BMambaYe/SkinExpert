package com.zhanghao.skinexpert.beans;

/**
 * Created by RockGao on 2016/12/23.
 */

/**
 * 测试问题的bean类
 */
public class QuestionBean {
    private String title;
    private String score;

    public QuestionBean() {
    }

    public QuestionBean(String title, String score) {
        this.title = title;
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
