package com.yuzhiyun.chemistry.model.entity;

/**
 * Created by yuzhiyun on 2016-07-21.
 * 包括除去选择题外的其他三种题型
 */
public class NoChoiceExercise {
    private String question;
    private String answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
