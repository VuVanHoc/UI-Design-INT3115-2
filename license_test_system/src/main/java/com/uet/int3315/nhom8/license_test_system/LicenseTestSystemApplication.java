package com.uet.int3315.nhom8.license_test_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class LicenseTestSystemApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LicenseTestSystemApplication.class, args);
	}
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.uet.int3315.nhom8.license_test_system.com.uet.int3315.nhom8.license_test_system.restcontroller"))
				.paths(PathSelectors.any())
				.build();
	}
	
}
