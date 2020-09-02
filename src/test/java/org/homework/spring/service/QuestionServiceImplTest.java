package org.homework.spring.service;


import org.junit.Before;
import org.junit.Test;

public class QuestionServiceImplTest {

    QuestionServiceImpl service;

    @Before
    public void init() {
        service = new QuestionServiceImpl();
    }

    private static final String TEST_STRING = "Как делать тесты?, Никак, Как-то постараться";

    @Test
    public void setData() {
        service.setData(TEST_STRING);
    }

    @Test
    public void printAllData() {
        service.setData(TEST_STRING);
        service.printAllData();
    }
}