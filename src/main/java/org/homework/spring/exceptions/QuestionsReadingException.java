package org.homework.spring.exceptions;

public class QuestionsReadingException extends RuntimeException {
    public QuestionsReadingException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
