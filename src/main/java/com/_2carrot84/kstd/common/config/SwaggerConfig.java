package com._2carrot84.kstd.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI springApi() {
		return new OpenAPI()
			.info(new Info()
				.title("KSTD 백엔드 개발 과제 - 이창근")
				.version("v1")
			);
	}
}