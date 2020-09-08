package org.homework.spring.service;

import org.homework.spring.dao.QuestionReaderDao;
import org.homework.spring.domain.Question;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public class QuestionService {

    public QuestionReaderDao readerDao;

    public CLIService cliService;

    public QuestionService(QuestionReaderDao readerDao, CLIService cliService) {
        this.readerDao = readerDao;
        this.cliService = cliService;
    }

    public void printAllData(List<Question> questions) {
        questions.forEach(question -> {
            cliService.printData("Вопрос:");
            cliService.printData(question.getQuestion());
            cliService.printData("Варианты ответа: ");
            question.getAnswers().forEach(a -> System.out.println("* " + a));
            cliService.printData("------------");
        });
    }

    public void startTest(Resource resource) throws IOException {

        List<Question> questions = readerDao.readQuestions(resource);
        printAllData(questions);

    }

}