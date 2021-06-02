package com.ramankutseika.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicUserApi() {
        return GroupedOpenApi.builder()
                .group("Employee")
                .pathsToMatch("/api/v1/employees/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenApi(@Value("${application-description:demo}") String appDescription,
                                 @Value("${application-version:0.01}") String appVersion) {
        return new OpenAPI().info(new Info().title("Application API")
                .version(appVersion)
                .description(appDescription));
    }
}
