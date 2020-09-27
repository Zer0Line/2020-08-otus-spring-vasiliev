package org.homework.spring.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
public class AppProps {

    private Locale locale;

    private String fileName;

    private String fileExtension;

    private int answersToPassTest;

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getFileName() {
        return fileName + locale + fileExtension;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getAnswersToPassTest() {
        return answersToPassTest;
    }

    public void setAnswersToPassTest(int answersToPassTest) {
        this.answersToPassTest = answersToPassTest;
    }
}
