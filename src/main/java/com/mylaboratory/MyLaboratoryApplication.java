package com.mylaboratory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MyLaboratoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyLaboratoryApplication.class, args);

	}

}
