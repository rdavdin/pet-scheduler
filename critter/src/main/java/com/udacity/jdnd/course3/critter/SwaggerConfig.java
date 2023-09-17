package com.udacity.jdnd.course3.critter;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI api(){
        return new OpenAPI().info(new Info().title("Pets Scheduler").description("Create pets, owners, and employees, and then schedule events for employees to provide services for pets").version("0.1"));
    }
}
