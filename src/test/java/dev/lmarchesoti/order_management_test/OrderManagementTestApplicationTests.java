package dev.lmarchesoti.order_management_test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"spring.kafka.listener.auto-startup=false"
})
class OrderManagementTestApplicationTests {

	@Test
	void contextLoads() {
	}

}
