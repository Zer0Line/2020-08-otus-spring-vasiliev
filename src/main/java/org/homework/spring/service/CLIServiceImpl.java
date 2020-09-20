package org.homework.spring.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CLIServiceImpl implements CLIService {

    private final Scanner scanner;

    public CLIServiceImpl() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void printData(String data) {
        System.out.println(data);
    }

    @Override
    public String readData() {
        return scanner.nextLine();
    }

    @Override
    public Integer readNumber() {
        return scanner.nextInt();
    }
}
