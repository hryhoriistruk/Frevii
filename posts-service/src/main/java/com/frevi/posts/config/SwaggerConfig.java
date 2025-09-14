package com.frevi.posts.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "post-service",
                version = "v1",
                description = "Responsible for post logic and their crud operations."
        )
)
public class SwaggerConfig {
}
