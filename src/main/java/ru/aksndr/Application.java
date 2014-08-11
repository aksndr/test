package ru.aksndr;

/**
 * Created by aksndr on 11.08.14.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@ComponentScan(basePackages = "ru.aksndr")
@Configuration
@EntityScan(basePackages = "ru.aksndr.model")
public class Application {
    private static final String MAX_REQUEST_SIZE = "150MB";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
