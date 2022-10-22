package com.acs.elearn.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.acs.elearn"})
public class ELearnWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ELearnWebApplication.class, args);
    }

}
