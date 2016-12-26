package com.zhanghao.skinexpert.beans;

/**
 * Created by RockGao on 2016/12/23.
 */

public class TestResultBean {
    private int minScore;
    private int maxScore;
    private String title;
    private String content;
    private int testType;
    private int skinCodeChar;

    public TestResultBean() {
    }

    public TestResultBean(int minScore, int maxScore, String title, String content, int testType, int skinCodeChar) {
        this.minScore = minScore;
        this.maxScore = maxScore;
        this.title = title;
        this.content = content;
        this.testType = testType;
        this.skinCodeChar = skinCodeChar;
    }

    public int getMinScore() {
        return minScore;
    }

    public void setMinScore(int minScore) {
        this.minScore = minScore;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTestType() {
        return testType;
    }

    public void setTestType(int testType) {
        this.testType = testType;
    }

    public int getSkinCodeChar() {
        return skinCodeChar;
    }

    public void setSkinCodeChar(int skinCodeChar) {
        this.skinCodeChar = skinCodeChar;
    }
}
