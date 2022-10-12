package com.acs.elearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableJpaAuditing // for SQl automatically add create time
public class ELearnWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ELearnWebApplication.class, args);
    }

}
