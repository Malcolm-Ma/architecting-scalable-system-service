package com.acs.elearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ELearnWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ELearnWebApplication.class, args);
    }

}
