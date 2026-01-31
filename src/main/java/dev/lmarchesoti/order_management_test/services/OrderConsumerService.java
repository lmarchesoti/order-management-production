package dev.lmarchesoti.order_management_test.services;

import dev.lmarchesoti.order_management_test.external.producta.dto.ProductAOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumerService {

    private final OrderService orderService;

    @KafkaListener(topics = "${app.kafka.topic}")
    public void listenOrders(ProductAOrder message) {
        orderService.processOrder(message);
    }

}
