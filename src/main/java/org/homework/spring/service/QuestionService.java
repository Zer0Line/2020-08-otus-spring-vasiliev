package org.homework.spring.service;

import org.homework.spring.dao.QuestionReaderDao;
import org.homework.spring.domain.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionReaderDao readerDao;

    private final CLIService cliService;

    private final Integer answersToPassTest;

    public QuestionService(QuestionReaderDao readerDao,
                           CLIService cliService,
                           @Value("${question.answersToPassTest}") Integer answers) {
        this.readerDao = readerDao;
        this.cliService = cliService;
        answersToPassTest = answers;
    }

    public void startTest() {
        List<Question> questions = readerDao.readQuestions();
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
        question.getAnswers().forEach(cliService::printData);
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