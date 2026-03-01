package dev.lmarchesoti.ordermanagement;

import dev.lmarchesoti.ordermanagement.adapter.producta.inbound.messaging.ProductAKafkaConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
//@ConfigurationPropertiesScan("dev.lmarchesoti.ordermanagement.adapter.producta.inbound.messaging")
@EnableConfigurationProperties(ProductAKafkaConfigurationProperties.class)
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class OrderManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementApplication.class, args);
	}

}
