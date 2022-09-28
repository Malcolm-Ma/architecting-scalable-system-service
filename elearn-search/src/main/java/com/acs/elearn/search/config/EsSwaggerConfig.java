package com.acs.elearn.search.config;

import com.acs.elearn.common.config.BaseSwaggerConfig;
import com.acs.elearn.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Es Swagger Config
 *
 * @author Mingze Ma
 */
@Configuration
@EnableSwagger2
public class EsSwaggerConfig extends BaseSwaggerConfig {
    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .selectors(RequestHandlerSelectors.basePackage("com.acs.elearn.search.controller"))
                .version("1.0")
                .title("eLearn Elastic Search API Docs")
                .description("eLearn API Docs for Elastic Search Application")
                .contactName("eLearn Dev Team")
                .contactEmail("elearn.acs.project@gmail.com")
                .build();
    }
}
