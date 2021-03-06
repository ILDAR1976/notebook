package ru.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class StartWebApplication extends SpringBootServletInitializer {
              
    
    public static void main(String[] args) {
        SpringApplication.run(StartWebApplication.class, args);
    } 

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        System.out.println("Running the spring");
        return builder.sources(StartWebApplication.class);
    }
}