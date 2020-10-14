package org.homework.spring.shell;

import org.homework.spring.service.QuestionService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ApplicationCommands {

    private final QuestionService questionService;

    public ApplicationCommands(QuestionService questionService) {
        this.questionService = questionService;
    }

    @ShellMethod(value = "start test", key = {"s", "start"})
    public void start() {
        questionService.startTest();
    }

}