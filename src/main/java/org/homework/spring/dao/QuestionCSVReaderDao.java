package org.homework.spring.dao;

import org.homework.spring.domain.Question;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuestionCSVReaderDao implements QuestionReaderDao {

    @Override
    public List<Question> readQuestions(Resource resource) throws IOException {
        StringBuilder sb = new StringBuilder();

        InputStream stream = resource.getInputStream();
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

        return questions;
    }
}
