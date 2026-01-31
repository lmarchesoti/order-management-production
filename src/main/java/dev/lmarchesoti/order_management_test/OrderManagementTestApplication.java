package dev.lmarchesoti.order_management_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@SpringBootApplication
public class OrderManagementTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementTestApplication.class, args);
	}

}
