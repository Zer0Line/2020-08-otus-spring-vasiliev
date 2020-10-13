package org.homework.spring;

import org.homework.spring.config.AppProps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}