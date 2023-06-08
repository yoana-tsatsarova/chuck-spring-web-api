package com.example.chuckspringwebapi;

import com.example.chuckspringwebapi.http.JokeFetcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
@SpringBootApplication
public class ChuckSpringRestApiApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ChuckSpringRestApiApplication.class, args);
        JokeFetcher app = context.getBean(JokeFetcher.class);
        System.out.println("Text: " + app.fetchJoke().text());
        System.out.println("Time: " + app.fetchJoke().responseAt());
        System.out.println("Id: " + app.fetchJoke().id());
    }
}
