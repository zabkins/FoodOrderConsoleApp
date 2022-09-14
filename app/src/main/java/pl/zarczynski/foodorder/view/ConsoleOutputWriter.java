package pl.zarczynski.foodorder.view;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleOutputWriter implements OutputWriter{
    @Override
    public void print(String message) {
        System.out.print(message);
    }

    @Override
    public void println(String message) {
        System.out.println(message);
    }
}
