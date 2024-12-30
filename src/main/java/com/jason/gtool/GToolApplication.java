package com.jason.gtool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class GToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(GToolApplication.class, args);
    }

}
