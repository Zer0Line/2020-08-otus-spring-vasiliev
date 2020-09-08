package org.homework.spring;

public class AppProps {

    private String questionsFileName;

    public String getQuestions() {
        return questionsFileName;
    }

    public void setQuestionsFileName(String questionsFileName) {
        this.questionsFileName = questionsFileName;
    }
}