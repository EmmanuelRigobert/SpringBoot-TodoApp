package com.emmanuel.todoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TodoapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoapiApplication.class, args);
	}

	@Bean
//The WebMvcConfigurer interface is used to implement the addCorsMappings method which is used to configure the CORS settings
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override//Below, we allow calls from all origins and all methods made to the API
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("*")
						.allowedOrigins("*");
			}
		};
	}
}
