package org.homework.spring.domain;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String question;

    private int rightAnswerNum;

    private final List<String> answers = new ArrayList<>();

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers.add(answers);
    }

    public int getRightAnswerNum() {
        return rightAnswerNum;
    }

    public void setRightAnswerNum(String rightAnswerNum) {
        this.rightAnswerNum = Integer.parseInt(rightAnswerNum);
    }
}
