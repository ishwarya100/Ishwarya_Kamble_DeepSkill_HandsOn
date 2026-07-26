package com.example.paymentservice.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// logs every circuit breaker state transition and fallback-triggering error event
@Component
public class CircuitBreakerEventLogger {

    private static final Logger logger = LoggerFactory.getLogger(CircuitBreakerEventLogger.class);

    private final CircuitBreakerRegistry registry;

    public CircuitBreakerEventLogger(CircuitBreakerRegistry registry) {
        this.registry = registry;
    }

    @PostConstruct
    public void subscribeToEvents() {
        registry.circuitBreaker("thirdPartyApi").getEventPublisher()
                .onStateTransition(event -> logger.info("Circuit breaker state transition: {}", event))
                .onError(event -> logger.warn("Circuit breaker recorded call error: {}", event))
                .onSuccess(event -> logger.debug("Circuit breaker recorded call success: {}", event));
    }
}
