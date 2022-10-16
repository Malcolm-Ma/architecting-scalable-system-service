package com.acs.elearn.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Global cross-domain configuration
 *
 * @author Mingze Ma
 */
@Configuration
public class GlobalCorsConfig {

    /**
     * Filters that allow cross-domain calls
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // Allow cross-domain calls for all domains
        config.addAllowedOriginPattern("*");
        // Allow sending cookies across
        config.setAllowCredentials(true);
        // Release all original header information
        config.addAllowedHeader("*");
        // Allow all request methods to be called across domains
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
