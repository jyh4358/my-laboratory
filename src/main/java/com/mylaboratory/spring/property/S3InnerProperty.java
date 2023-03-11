package com.mylaboratory.spring.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "s3")
@Getter
@RequiredArgsConstructor
public class S3InnerProperty {
    private final String accessKey;
    private final String secretKey;
    private final BucketProperty bucket;

    @Getter
    @RequiredArgsConstructor
    public static class BucketProperty {
        private final String stage;
        private final String production;

    }
}
