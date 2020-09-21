package org.homework.spring.dao;

import org.homework.spring.domain.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class QuestionCSVReaderDao implements QuestionReaderDao {

    private final String filePath;

    private final String answers;

    public QuestionCSVReaderDao(@Value("${question.fileName}") String filePath,
                                @Value("${question.rightAnswers}") String answers) {
        this.filePath = filePath;
        this.answers = answers;
    }

    @Override
    public List<Question> readQuestions() {
        StringBuilder sb = new StringBuilder();

        InputStream stream = getClass().getClassLoader().getResourceAsStream(filePath);
        InputStreamReader streamReader = new InputStreamReader(Objects.requireNonNull(stream));

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
