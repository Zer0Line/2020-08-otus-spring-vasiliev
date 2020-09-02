package org.homework.spring.service;

import org.homework.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    List<Question> questions = new ArrayList<>();

    @Override
    public void setData(String dataFromResource) {
        parseDataFromFile(dataFromResource);
    }

    @Override
    public void printAllData() {
        questions.forEach(question -> {
            System.out.println("Вопрос:");
            System.out.println(question.getQuestion());
            System.out.println("Варианты ответа: ");
            question.getAnswers().forEach(a -> System.out.println("* " + a));
            System.out.println("------------");
        });
    }

    private void parseDataFromFile(String data) {

        String[] questionAndAnswer = data.split(";");

        for (String qAndA : questionAndAnswer) {
            Question q = new Question();

            String[] split = qAndA.split(",");

            for (int i = 0; i < split.length; i++) {
                String d = split[i];
                if (i == 0) {
                    q.setQuestion(d);
                } else {
                    q.setAnswers(d);
                }
            }

            questions.add(q);
        }
    }

}