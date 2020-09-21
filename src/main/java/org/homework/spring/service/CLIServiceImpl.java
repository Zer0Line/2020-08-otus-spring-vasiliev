package org.homework.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class CLIServiceImpl implements CLIService {

    private final Scanner scanner;

    private final PrintStream printStream;

    public CLIServiceImpl(
            @Value("#{ T(java.lang.System).in}") InputStream inStream,
            @Value("#{ T(java.lang.System).out}") PrintStream printStream
    ) {
        scanner = new Scanner(inStream);
        this.printStream = printStream;
    }

    @Override
    public void printData(String data) {
        printStream.println(data);
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
