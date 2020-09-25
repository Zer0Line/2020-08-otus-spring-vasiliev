package org.homework.spring.dao;

import org.homework.spring.config.AppProps;
import org.homework.spring.domain.Question;
import org.homework.spring.exceptions.QuestionsReadingException;
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

    public QuestionCSVReaderDao(AppProps appProps) {
        this.filePath = appProps.getFileName();
    }

    @Override
    public List<Question> readQuestions() throws QuestionsReadingException {
        StringBuilder sb = new StringBuilder();
        InputStream stream = getClass().getClassLoader().getResourceAsStream(filePath);

        try {
            InputStreamReader streamReader = new InputStreamReader(Objects.requireNonNull(stream));
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append(";");
            }
        } catch (IOException e) {
            throw new QuestionsReadingException(e.getMessage(), e);
        }

        return parseDataFromFile(sb.toString());
    }


    private List<Question> parseDataFromFile(String data) {

        List<Question> questions = new ArrayList<>();

        String[] questionAndAnswer = data.split(";");

        for (String qAndA : questionAndAnswer) {
            Question q = new Question();

            String[] split = qAndA.split(",");

            for (int i = 0; i < split.length; i++) {
                String d = split[i];
                switch (i) {
                    case 0:
                        q.setQuestion(d);
                        break;
                    case 1:
                        q.setRightAnswerNum(d);
                        break;
                    default:
                        q.setAnswers(d);
                        break;
                }
            }

            questions.add(q);
        }

        return questions;
    }
}
