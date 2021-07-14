package com.jarida.server.jaridaserver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableJpaAuditing //Enabling JPA Auditing
@EnableSwagger2
public class JaridaServerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(JaridaServerApplication.class, args);
	}

	@Bean
	@Primary
	public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder){
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		return objectMapper;
	}

	// http://localhost:8080/swagger-ui.html#/
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.consumes(Sets.newHashSet("application/json"))
				.produces(Sets.newHashSet("application/json"))
				.protocols(Sets.newHashSet("http", "https"))
				.groupName("B Jarida - V1")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.jarida.server.jaridaserver"))
				.paths(PathSelectors.ant("/api/v1/**"))
				.build()
				.apiInfo(apiInfo())
				.forCodeGeneration(true);

	}

	@Bean
	public Docket studentApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.consumes(Sets.newHashSet("application/json"))
				.produces(Sets.newHashSet("application/json"))
				.protocols(Sets.newHashSet("http", "https"))
				.groupName("A Student - V2")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.jarida.server.jaridaserver"))
				.paths(PathSelectors.ant("/api/v2/**"))
				.build()
				.apiInfo(apiInfo())
				.forCodeGeneration(true);

	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"My REST API",
				"Some custom description of API.",
				"API TOS",
				"Terms of service",
				new Contact("John Doe", "www.example.com", "myeaddress@company.com"),
				"License of API", "API license URL", Collections.emptyList());
	}



}
