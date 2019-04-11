package com.api.pgc.core.APIRestPGC;

// import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.boot.builder.SpringApplicationBuilder;



@EnableJpaRepositories(basePackages = "com.api.pgc.core.APIRestPGC.repository")
@SpringBootApplication
public class ApiRestPgcApplication extends SpringBootServletInitializer {

	/**
	 * Used when run as JAR
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApiRestPgcApplication.class, args);
	}

	/**
	 * Used when run as WAR
	 */
	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}*/
}
