package org.homework.spring.dao;

import org.homework.spring.domain.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("QuestionCSVReaderDao implementation")
@SpringBootTest
class QuestionCSVReaderDaoTest {

    @Autowired
    private QuestionCSVReaderDao questionCSVReaderDao;

    @DisplayName("read questions from csv file")
    @Test
    void readQuestions() {
        List<Question> questions = questionCSVReaderDao.readQuestions();
        assertThat(questions).isNotEmpty();
    }
}