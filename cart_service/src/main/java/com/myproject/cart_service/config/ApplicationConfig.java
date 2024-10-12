package com.myproject.cart_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author nguyenle
 */
@Configuration
@EnableAsync
public class ApplicationConfig {

    private final int EVENT_THREAD_CORE_POOL_SIZE = 1;

    private final int EVENT_THREAD_MAX_POOL_SIZE = 5;

    private final int EVENT_THREAD_LIFE_TIME_IN_MIN = 1;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "taskExecutor")
    public Executor eventTaskExecutor() {
        ThreadPoolExecutor executors = new ThreadPoolExecutor(
                EVENT_THREAD_CORE_POOL_SIZE,
                EVENT_THREAD_MAX_POOL_SIZE,
                EVENT_THREAD_LIFE_TIME_IN_MIN,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>()
        );
        return executors;
    }

}
