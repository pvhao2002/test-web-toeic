package com.example.webtoeic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

@SpringBootApplication(exclude = { JacksonAutoConfiguration.class })
public class WebtoeicApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebtoeicApplication.class, args);
    }

}
