package com.mylaboratory;

import com.mylaboratory.spring.property.AWSProperty;
import com.mylaboratory.spring.property.RegionProperty;
import com.mylaboratory.spring.property.S3InnerProperty;
import com.mylaboratory.spring.property.S3Property;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MyLaboratoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyLaboratoryApplication.class, args);

	}

}
