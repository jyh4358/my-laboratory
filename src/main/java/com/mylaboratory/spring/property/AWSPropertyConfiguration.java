package com.mylaboratory.spring.property;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({S3Property.class, AWSProperty.class, RegionProperty.class, S3InnerProperty.class})
public class AWSPropertyConfiguration {
}
