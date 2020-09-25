package org.homework.spring.service;

import org.homework.spring.config.AppProps;
import org.homework.spring.dao.QuestionReaderDao;
import org.homework.spring.domain.Question;
import org.homework.spring.exceptions.QuestionsReadingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final LocalizationService localizationService;

    private final QuestionReaderDao readerDao;

    private final CLIService cliService;

    private final int answersToPassTest;

    public QuestionService(LocalizationService localizationService,
                           QuestionReaderDao readerDao,
                           CLIService cliService,
                           AppProps appProps) {
        this.localizationService = localizationService;
        this.readerDao = readerDao;
        this.cliService = cliService;
        answersToPassTest = appProps.getAnswersToPassTest();
    }

    public void startTest() {
        List<Question> questions;
        try {
            questions = readerDao.readQuestions();
            askFullName();
            int rightAnswers = proceedQuestions(questions);
            printResult(rightAnswers);
        } catch (QuestionsReadingException e) {
            e.printStackTrace();
        }
    }

    private void askFullName() {
        cliService.printData(localizationService.getMessage("test.askFullName"));
        String name = cliService.readData();
        String welcomeMessage = localizationService.getMessage(
                "test.welcomeMessage", new String[]{name});
        cliService.printData(welcomeMessage);
    }

    private int proceedQuestions(List<Question> questions) {
        int rightAnswers = 0;

        for (Question question : questions) {
            int answerNum = askQuestion(question);
            if (question.getRightAnswerNum() == answerNum) {
                rightAnswers++;
            }
        }

        return rightAnswers;
    }

    private int askQuestion(Question question) {
        cliService.printData(question.getQuestion());
        question.getAnswers().forEach(cliService::printData);
        return cliService.readNumber();
    }

    private void printResult(int rightAnswers) {
        String result = localizationService.getMessage("test.rightAnswers",
                new String[]{String.valueOf(rightAnswers)});

        result += (rightAnswers > answersToPassTest)
                ? localizationService.getMessage("test.success")
                : localizationService.getMessage("test.fail");

        cliService.printData(result);
    }
}