package org.homework.spring.service;

import org.homework.spring.dao.QuestionReaderDao;
import org.homework.spring.domain.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class QuestionService {

    public QuestionReaderDao readerDao;

    public CLIService cliService;

    @Value("${question.answersToPassTest}")
    private Integer answersToPassTest;

    public QuestionService(QuestionReaderDao readerDao, CLIService cliService) {
        this.readerDao = readerDao;
        this.cliService = cliService;
    }

    public void startTest(AnnotationConfigApplicationContext context) throws IOException {
        List<Question> questions = readerDao.readQuestions(context);
        askFullName();
        int rightAnswers = proceedQuestions(questions);
        printResult(rightAnswers);
    }

    private void askFullName() {
        cliService.printData("Введите имя фамилию:");
        String name = cliService.readData();
        cliService.printData("Доброго времени суток " + name + "! Пройдите тест");
    }

    public int proceedQuestions(List<Question> questions) {
        int rightAnswers = 0;

        for (Question question : questions) {
            Integer answerNum = askQuestion(question);
            if (question.getRightAnswerNum().equals(answerNum)) {
                rightAnswers++;
            }
        }

        return rightAnswers;
    }

    private Integer askQuestion(Question question) {
        cliService.printData(question.getQuestion());
        question.getAnswers().forEach(q -> cliService.printData(q));
        return cliService.readNumber();
    }

    private void printResult(Integer rightAnswers) {
        String result = "Венрых ответов: " + rightAnswers + ". ";

        result += (rightAnswers > answersToPassTest)
                ? "Вы прошли тест"
                : "Тест не пройден";

        cliService.printData(result);
    }
}