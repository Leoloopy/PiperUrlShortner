package com.piper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"service"})
@ComponentScan(basePackages = {"repositories"})
@EnableMongoRepositories(basePackages = {"repositories"})
public class Main {
    public static void main(String[] args) {
SpringApplication.run(Main.class, args);
    }
}
