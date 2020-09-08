package org.homework.spring.dao;

import org.homework.spring.domain.Question;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public interface QuestionReaderDao {

    List<Question> readQuestions(Resource resource) throws IOException;

}
