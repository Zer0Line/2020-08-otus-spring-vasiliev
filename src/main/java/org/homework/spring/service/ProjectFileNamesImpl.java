package org.homework.spring.service;

public class ProjectFileNamesImpl implements ProjectFileNames {

    private String questionsFileName;

    @Override
    public String getQuestions() {
        return questionsFileName;
    }

    public void setQuestionsFileName(String questionsFileName) {
        this.questionsFileName = questionsFileName;
    }
}