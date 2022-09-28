package com.acs.elearn.common.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import springfox.documentation.RequestHandler;

import java.util.function.Predicate;

/**
 * Swagger custom properties
 * @author Mingze Ma
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class SwaggerProperties {

    /**
     * API doc base generating packages
     */
    private Predicate<RequestHandler> selectors;

    /**
     * doc title
     */
    private String title;

    /**
     * doc description
     */
    private String description;

    /**
     * doc version
     */
    private String version;

    /**
     * doc contact name
     */
    private String contactName;

    /**
     * doc contact url
     */
    private String contactUrl;

    /**
     * doc contact email
     */
    private String contactEmail;

}
