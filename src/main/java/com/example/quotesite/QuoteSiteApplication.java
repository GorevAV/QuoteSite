package com.example.quotesite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;


@SpringBootApplication
public class QuoteSiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuoteSiteApplication.class, args);
    }

}
