package com.myproject.cart_service.kafka;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 */
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

	private final int CORE_POOL_SIZE = 10;

	private final int MAX_POOL_SIZE = 20;

	private final int KEEP_ALIVE_TIME_IN_MIN = 5;


	private final ExecutorService executor = new ThreadPoolExecutor(
		CORE_POOL_SIZE,
		MAX_POOL_SIZE,
		KEEP_ALIVE_TIME_IN_MIN,
		TimeUnit.MINUTES,
		new LinkedBlockingQueue<>()
	);

	private final KafkaTemplate<String, Object> kafkaTemplate;

	public void sendMessage(String topic, Object message) {
		executor.execute(
			() -> kafkaTemplate.send(topic, message)
		);
	}

}
