package com.myproject.payment_service.kafka;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author nguyenle
 * @since 7:08 PM Sat 10/12/2024
 */
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final int CORE_POOL_SIZE = 1;

    private final int MAX_POOL_SIZE = 5;

    private final int THREAD_LIFE_TIME_IN_MIN = 1;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private ThreadPoolExecutor executor;

    @PostConstruct
    private void initialize() {
        executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                THREAD_LIFE_TIME_IN_MIN,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>()
        );
    }

    public void sendMessage(String topic, Object message) {
        executor.execute(
                () -> kafkaTemplate.send(topic, message)
        );
    }

}
