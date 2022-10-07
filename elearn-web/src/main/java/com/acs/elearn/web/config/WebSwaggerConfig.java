package com.acs.elearn.web.config;

import com.acs.elearn.common.config.BaseSwaggerConfig;
import com.acs.elearn.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Web Swagger Config
 *
 * @author Mingze Ma
 */
@Configuration
@EnableSwagger2
public class WebSwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                // com.acs.elearn.search.controller
                .selectors(RequestHandlerSelectors.basePackage("com.acs.elearn.web.controllers")
                        .or(RequestHandlerSelectors.basePackage("com.acs.elearn.search.controller")))
                .version("1.0")
                .title("eLearn Web API Docs")
                .description("eLearn API Docs for Main Web Application")
                .contactName("eLearn Dev Team")
                .contactEmail("elearn.acs.project@gmail.com")
                .build();
    }
}
