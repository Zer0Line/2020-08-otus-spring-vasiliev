package org.homework.spring.dao;

import org.homework.spring.domain.Question;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.List;

public interface QuestionReaderDao {

    List<Question> readQuestions(ApplicationContext ctx) throws IOException;

}
