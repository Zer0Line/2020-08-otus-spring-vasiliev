package org.homework.spring;

import org.homework.spring.config.AppProps;
import org.homework.spring.service.QuestionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
class Main {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);

        ctx.getBean(QuestionService.class).startTest();
    }

}