package com.example.homework.global;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Homework 프로젝트 API")
                        .version("1.0.0")
                        .description("멤버 및 콘텐츠 CRUD API 명세서입니다."));
    }
}
