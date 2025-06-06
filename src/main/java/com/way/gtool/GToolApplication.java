package com.way.gtool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@ComponentScan(basePackages = {"com.way"})
@SpringBootApplication
public class GToolApplication {
    public static void main(String[] args) {
        SpringApplication.run(GToolApplication.class, args);
    }

}
