package com.seungwon.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.NonNull;

@Configuration

public class WebConfig implements WebMvcConfigurer {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(@NonNull CorsRegistry corsRegistry) {
				corsRegistry.addMapping("/**")
						.allowedOriginPatterns("http://localhost:3000")
						.allowedOriginPatterns("*")
						.allowedHeaders("*")
						.allowedMethods("*")
						.exposedHeaders("Authorization", "RefreshToken");
			}
		};
	}
}
