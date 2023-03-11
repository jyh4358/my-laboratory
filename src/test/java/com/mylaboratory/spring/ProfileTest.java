package com.mylaboratory.spring;

import com.mylaboratory.spring.property.AWSProperty;
import com.mylaboratory.spring.property.S3Property;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
public class ProfileTest {

    @Value("${aws.s3.access-key}")
    private String accessKey;

    @Value("${aws.s3.secret-key}")
    private String secretKey;

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Autowired
    private S3Property s3Property;

    @Autowired
    private AWSProperty awsProperty;


    @DisplayName("@Value 어노테이션 사용")
    @Test
    public void property_test1() {
        Assertions.assertThat(accessKey).isEqualTo("access-key value");
        Assertions.assertThat(secretKey).isEqualTo("secret-key value");
        Assertions.assertThat(bucket).isEqualTo("bucket value");
    }

    @DisplayName("@ConfigurationProperties 어노테이션 사용")
    @Test
    public void property_test2() {
        Assertions.assertThat(s3Property.getAccessKey()).isEqualTo("access-key value");
        Assertions.assertThat(s3Property.getSecretKey()).isEqualTo("secret-key value");
        Assertions.assertThat(s3Property.getBucket()).isEqualTo("bucket value");
    }

    @DisplayName("@ConfigurationProperties 어노테이션 사용 - 여러개 Property")
    @Test
    public void property_test3() {
        Assertions.assertThat(awsProperty.getS3Property().getAccessKey()).isEqualTo("access-key value");
        Assertions.assertThat(awsProperty.getS3Property().getSecretKey()).isEqualTo("secret-key value");
        Assertions.assertThat(awsProperty.getS3Property().getBucket()).isEqualTo("bucket value");
        Assertions.assertThat(awsProperty.getRegionProperty().getRegionStatic()).isEqualTo("region-static value");
    }

}
