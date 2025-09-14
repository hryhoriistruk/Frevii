package com.frevi.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class GatewayConfig {
    private static final String FALLBACK_URL = "forward:/fallbackRoute";

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user_service",
                        r -> r.path("/api/v1/users/**")
                                .filters(
                                        f ->
                                                f.circuitBreaker(c ->
                                                        c.setName("userServiceCB")
                                                                .setFallbackUri(FALLBACK_URL))
                                ).uri("lb://userService")
                )
                .route("messenger_service",
                        r -> r.path("/api/v1/messenger/**")
                                .filters(
                                        f ->
                                                f.circuitBreaker(c ->
                                                        c.setName("messengerServiceCB")
                                                                .setFallbackUri(FALLBACK_URL))
                                ).uri("lb://messengerService")
                )
                .route("order",
                        r -> r.path("/api/v1/orders/**")
                                .filters(
                                        f ->
                                                f.circuitBreaker(c ->
                                                        c.setName("orderServiceCB")
                                                                .setFallbackUri(FALLBACK_URL))
                                ).uri("lb://orderService")
                )
                .route("assistance_service",
                        r -> r.path("/api/v1/assistance/**")
                                .filters(
                                        f ->
                                                f.circuitBreaker(c ->
                                                        c.setName("assistanceServiceCB")
                                                                .setFallbackUri(FALLBACK_URL))
                                ).uri("lb://assistanceService")
                )
                .route("payment_service",
                        r -> r.path("/api/v1/payment/**")
                                .filters(
                                        f ->
                                                f.circuitBreaker(c ->
                                                        c.setName("paymentServiceCB")
                                                                .setFallbackUri(FALLBACK_URL))
                                ).uri("lb://paymentService")
                )
                .route("admin_service",
                        r -> r.path("/api/v1/admin/**")
                                .filters(
                                        f ->
                                                f.circuitBreaker(c ->
                                                        c.setName("adminServiceCB")
                                                                .setFallbackUri(FALLBACK_URL))
                                ).uri("lb://adminService")
                )
                .route("analytics_service",
                        r -> r.path("/api/v1/analytics/**")
                                .filters(
                                        f ->
                                                f.circuitBreaker(c ->
                                                        c.setName("analyticsServiceCB")
                                                                .setFallbackUri(FALLBACK_URL))
                                ).uri("lb://analyticsService")
                )
                .route("notification_service",
                        r -> r.path("/api/v1/notify/**")
                                .filters(
                                        f ->
                                                f.circuitBreaker(c ->
                                                        c.setName("notificationServiceCB")
                                                                .setFallbackUri(FALLBACK_URL))
                                ).uri("lb://notificationService")
                )
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackHandle() {
        return RouterFunctions
                .route()
                .GET("/fallback", request
                        -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                .bodyValue(FALLBACK_URL))
                .build();

    }
}
