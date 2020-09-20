package org.homework.spring.dao;

import org.homework.spring.domain.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionCSVReaderDao implements QuestionReaderDao {

    @Value("${question.fileName}")
    private String filePath;

    @Value("${question.rightAnswers}")
    private String answers;

    @Override
    public List<Question> readQuestions(ApplicationContext ctx) throws IOException {
        StringBuilder sb = new StringBuilder();

        InputStream stream = ctx.getResource(filePath).getInputStream();
        InputStreamReader streamReader = new InputStreamReader(stream);

        try (BufferedReader bufferedReader = new BufferedReader(streamReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append(";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parseDataFromFile(sb.toString());
    }


    private List<Question> parseDataFromFile(String data) {

        List<Question> questions = new ArrayList<>();

        String[] questionAndAnswer = data.split(";");

        String[] rightAnswers = answers.split(",");

        for (int i = 0; i < questionAndAnswer.length; i++) {
            String qAndA = questionAndAnswer[i];
            Question q = new Question();

            String[] split = qAndA.split(",");

            for (int j = 0; j < split.length; j++) {
                String d = split[j];
                if (j == 0) {
                    q.setQuestion(d);
                } else {
                    q.setAnswers(d);
                }
            }

            q.setRightAnswerNum(rightAnswers[i]);
            questions.add(q);
        }

        return questions;
    }
}
