package pl.zarczynski.foodorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "pl.zarczynski.foodorder")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}