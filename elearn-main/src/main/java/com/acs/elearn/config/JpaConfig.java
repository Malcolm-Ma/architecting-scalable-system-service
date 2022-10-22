package com.acs.elearn.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Mingze Ma
 */
@EnableJpaRepositories(basePackages = "com.acs.elearn.dao.repositories")
@EntityScan("com.acs.elearn.dao.model")
@EnableJpaAuditing // for SQl automatically add create time
@Configuration
public class JpaConfig {
}
