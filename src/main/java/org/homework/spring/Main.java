package org.homework.spring;

import org.homework.spring.service.QuestionService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

@ComponentScan
@PropertySource("classpath:application.properties")
class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        try {
            context.getBean(QuestionService.class).startTest(context);
        } catch (IOException ignored) {
        }
    }

}