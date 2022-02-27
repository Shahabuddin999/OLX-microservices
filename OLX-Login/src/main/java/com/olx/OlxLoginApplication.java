package com.olx;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class OlxLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxLoginApplication.class, args);
	}
	@Bean
	public Docket getCustomizedDocket() {
	return new Docket(DocumentationType.SWAGGER_2)
	.select()
	.apis(RequestHandlerSelectors.basePackage("com.olx"))
	//.paths(PathSelectors.ant("/stockapp/*"))
	.paths(PathSelectors.any())
	.build().apiInfo(getApiInfo());
	}
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	private ApiInfo getApiInfo() {
		ApiInfo apiInfo = new ApiInfo("This is my Title", "This is my Description", "This is my version 2.0.1", "Terms of service Shahab", new Contact("Shahabuddin Contact", "http://zensar.com", "ansari.shahabuddin999@gmail.com") , "My license : __", "my license URL : __", new ArrayList<VendorExtension>());
		return apiInfo;
	}
}
