package org.homework.spring.dao;

import org.homework.spring.domain.Question;

import java.util.List;

public interface QuestionReaderDao {

    List<Question> readQuestions();

}
