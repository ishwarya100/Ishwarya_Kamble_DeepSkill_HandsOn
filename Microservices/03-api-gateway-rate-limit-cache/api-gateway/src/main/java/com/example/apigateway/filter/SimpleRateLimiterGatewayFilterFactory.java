package com.example.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// simple in-memory fixed-window rate limiter, keyed by client IP
// (no Redis needed, unlike Spring Cloud Gateway's built-in RequestRateLimiter)
@Component
public class SimpleRateLimiterGatewayFilterFactory
        extends AbstractGatewayFilterFactory<SimpleRateLimiterGatewayFilterFactory.Config> {

    private final ConcurrentHashMap<String, Window> windows = new ConcurrentHashMap<>();

    public SimpleRateLimiterGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String clientKey = exchange.getRequest().getRemoteAddress() != null
                    ? exchange.getRequest().getRemoteAddress().getAddress().getHostAddress()
                    : "unknown";

            Window window = windows.computeIfAbsent(clientKey, k -> new Window());
            long nowSeconds = System.currentTimeMillis() / 1000;

            // reset the counter once the window has elapsed
            if (nowSeconds - window.windowStart.get() >= config.getWindowSeconds()) {
                window.windowStart.set(nowSeconds);
                window.count.set(0);
            }

            if (window.count.incrementAndGet() > config.getLimit()) {
                exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                return exchange.getResponse().setComplete();
            }
            return chain.filter(exchange);
        };
    }

    private static class Window {
        AtomicInteger count = new AtomicInteger(0);
        AtomicInteger windowStart = new AtomicInteger((int) (System.currentTimeMillis() / 1000));
    }

    public static class Config {
        private int limit = 10;
        private int windowSeconds = 60;

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getWindowSeconds() {
            return windowSeconds;
        }

        public void setWindowSeconds(int windowSeconds) {
            this.windowSeconds = windowSeconds;
        }
    }
}
