package org.homework.spring.service;

import org.homework.spring.config.AppProps;
import org.homework.spring.dao.QuestionReaderDao;
import org.homework.spring.domain.Question;
import org.homework.spring.exceptions.QuestionsReadingException;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final MessageSource messageSource;

    private final QuestionReaderDao readerDao;

    private final CLIService cliService;

    private final int answersToPassTest;

    private final AppProps appProps;

    public QuestionService(MessageSource messageSource, QuestionReaderDao readerDao,
                           CLIService cliService,
                           AppProps appProps) {
        this.messageSource = messageSource;
        this.readerDao = readerDao;
        this.cliService = cliService;
        this.appProps = appProps;
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
        cliService.printData(messageSource.getMessage("test.askFullName", null, appProps.getLocale()));
        String name = cliService.readData();
        String welcomeMessage = messageSource.getMessage(
                "test.welcomeMessage", new String[]{name}, appProps.getLocale());
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
        String result = messageSource.getMessage("test.rightAnswers",
                new String[]{String.valueOf(rightAnswers)}, appProps.getLocale());

        result += (rightAnswers > answersToPassTest)
                ? messageSource.getMessage("test.success", null, appProps.getLocale())
                : messageSource.getMessage("test.fail", null, appProps.getLocale());

        cliService.printData(result);
    }
}