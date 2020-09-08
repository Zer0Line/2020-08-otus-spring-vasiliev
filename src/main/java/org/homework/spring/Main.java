package org.homework.spring;

import org.homework.spring.service.QuestionService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

class Main {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");

        AppProps appProps = context.getBean(AppProps.class);

        QuestionService questionService = context.getBean(QuestionService.class);

        try {
            questionService.startTest(context.getResource(appProps.getQuestions()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}