package com.myproject.notification_service.config.resilience4j;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @author nguyenle
 */
@Configuration
public class Resilience4jConfig {

	@Bean
	public CircuitBreakerRegistry circuitBreakerRegistry() {
		CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
			.slidingWindowSize(10)
			.failureRateThreshold(50.0f)
			.waitDurationInOpenState(Duration.ofSeconds(5))
			.build();

		CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(circuitBreakerConfig);

		registry.circuitBreaker("authService");

		return registry;
	}

	@Bean
	public RetryRegistry retryRegistry() {
		RetryConfig retryConfig = RetryConfig.custom()
			.maxAttempts(3)
			.waitDuration(Duration.ofSeconds(1))
			.build();

		RetryRegistry registry = RetryRegistry.of(retryConfig);

		registry.retry("authService");

		return registry;
	}

}
