package com.acs.elearn.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ELearn Search Application
 *
 * @author Mingze Ma
 */
@SpringBootApplication(scanBasePackages = "com.acs.elearn")
public class ELearnSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ELearnSearchApplication.class, args);
    }

}
