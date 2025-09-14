package com.frevi.messaging.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "messaging-service",
                version = "v1",
                description = "Responsible for messenger backend implementation."
        )
)
public class SwaggerConfig {
}
