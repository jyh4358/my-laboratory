package com.mylaboratory.spring.property;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
public class PropertyTest {

    @Value("${aws.s3.access-key}")
    private String accessKey;

    @Value("${aws.s3.secret-key}")
    private String secretKey;

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Value("${test-property.value1}")
    private boolean booleanValue;

    @Value("${test-property.value1}")
    private String stringValue1;

    @Value("${test-property.value2}")
    private int intValue;

    @Value("${test-property.value2}")
    private String stringValue2;

    @Autowired
    private S3Property s3Property;

    @Autowired
    private AWSProperty awsProperty;

    @Autowired
    private S3InnerProperty s3InnerProperty;


    @DisplayName("@Value 어노테이션 사용")
    @Test
    void property_test1() {
        Assertions.assertThat(accessKey).isEqualTo("access-key value");
        Assertions.assertThat(secretKey).isEqualTo("secret-key value");
        Assertions.assertThat(bucket).isEqualTo("bucket value");
    }

    @DisplayName("@Value 어노테이션 타입 안정성")
    @Test
    void property_test2() {
        // boolean, String type으로 값을 가져올 수 있다.
        Assertions.assertThat(booleanValue).isTrue();
        Assertions.assertThat(stringValue1).isEqualTo("true");

        // int, String type으로 값을 가져올 수 있다.
        Assertions.assertThat(intValue).isEqualTo(1234);
        Assertions.assertThat(stringValue2).isEqualTo("1234");
    }

    @DisplayName("@ConfigurationProperties 어노테이션 사용")
    @Test
    void property_test3() {
        Assertions.assertThat(s3Property.getAccessKey()).isEqualTo("access-key value");
        Assertions.assertThat(s3Property.getSecretKey()).isEqualTo("secret-key value");
        Assertions.assertThat(s3Property.getBucket()).isEqualTo("bucket value");
    }

    @DisplayName("@ConfigurationProperties 어노테이션 사용 - 여러개 Property")
    @Test
    void property_test4() {
        Assertions.assertThat(awsProperty.getS3().getAccessKey()).isEqualTo("access-key value");
        Assertions.assertThat(awsProperty.getS3().getSecretKey()).isEqualTo("secret-key value");
        Assertions.assertThat(awsProperty.getS3().getBucket()).isEqualTo("bucket value");
        Assertions.assertThat(awsProperty.getRegion().getRegionStatic()).isEqualTo("region-static value");
    }

    @DisplayName("@Configurati@ConfigurationProperties 어노테이션 사용 - inner class 사용")
    @Test
    void property_test5() {
        Assertions.assertThat(s3InnerProperty.getAccessKey()).isEqualTo("access-key value");
        Assertions.assertThat(s3InnerProperty.getSecretKey()).isEqualTo("secret-key value");
        Assertions.assertThat(s3InnerProperty.getBucket().getStage()).isEqualTo("stage-bucket value");
        Assertions.assertThat(s3InnerProperty.getBucket().getProduction()).isEqualTo("production-bucket value");
    }

}
