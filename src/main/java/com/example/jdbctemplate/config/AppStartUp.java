package com.example.jdbctemplate.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
@Component
public class AppStartUp implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
    System.out.println("hello world");   
    }
    
}
