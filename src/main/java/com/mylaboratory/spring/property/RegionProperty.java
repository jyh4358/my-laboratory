package com.mylaboratory.spring.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws.region")
@Getter
@RequiredArgsConstructor
public class RegionProperty {
    private final String regionStatic;
}
