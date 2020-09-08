package org.homework.spring.domain;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String question;

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
}
