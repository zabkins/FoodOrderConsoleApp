package pl.zarczynski.foodorder.view;

import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class ConsoleInputReader implements InputReader{
    private final Scanner consoleScanner = new Scanner(System.in);
    @Override
    public String nextLine() {
        return consoleScanner.nextLine();
    }
}
