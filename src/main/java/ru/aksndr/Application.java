package ru.aksndr;

/**
 * Created by aksndr on 11.08.14.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.aksndr.datalayer.UsersRepository;

@EnableAutoConfiguration
@ComponentScan(basePackages = "ru.aksndr")
@Configuration
@EntityScan(basePackages = "ru.aksndr.model")
@EnableJpaRepositories(basePackageClasses = UsersRepository.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
