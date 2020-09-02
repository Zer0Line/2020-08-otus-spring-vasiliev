package org.homework.spring;

import org.homework.spring.service.ProjectFileNames;
import org.homework.spring.service.QuestionService;
import org.homework.spring.utils.ProjectFileReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;

class Main {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");

        ProjectFileNames projectFileNames = context.getBean(ProjectFileNames.class);
        QuestionService questionService = context.getBean(QuestionService.class);

        Resource questionResource = context.getResource(projectFileNames.getQuestions());

        try {
            String data =
                    ProjectFileReader.readFromInputStream(questionResource.getInputStream());
            questionService.setData(data);
            questionService.printAllData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}