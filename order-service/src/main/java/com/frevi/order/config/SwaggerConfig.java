package com.frevi.order.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "order-service",
                version = "v1",
                description = "Responsible for order logic and their crud operations."
        )
)
public class SwaggerConfig {
}
