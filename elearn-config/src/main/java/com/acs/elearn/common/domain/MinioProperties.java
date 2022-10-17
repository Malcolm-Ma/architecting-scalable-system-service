package com.acs.elearn.common.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    /**
     * end point
     */
    private String endpoint;

    /**
     * username
     */
    private String accessKey;

    /**
     * password
     */
    private String secretKey;

    /**
     * bucket name
     */
    private String bucketName;
}
