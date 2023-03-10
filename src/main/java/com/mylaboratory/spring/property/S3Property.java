package com.mylaboratory.spring.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws.s3")
@Getter
@RequiredArgsConstructor
public class S3Property {
    private final String accessKey;
    private final String secretKey;
    private final String bucket;
}
