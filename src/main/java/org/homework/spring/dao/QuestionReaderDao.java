package org.homework.spring.dao;

import org.homework.spring.domain.Question;
import org.homework.spring.exceptions.QuestionsReadingException;

import java.util.List;

public interface QuestionReaderDao {

    List<Question> readQuestions() throws QuestionsReadingException;

}
