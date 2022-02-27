package com.zensar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OlxApiGatwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxApiGatwayApplication.class, args);
	}

}
