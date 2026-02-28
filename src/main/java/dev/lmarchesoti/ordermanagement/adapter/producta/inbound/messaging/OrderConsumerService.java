package dev.lmarchesoti.ordermanagement.adapter.producta.inbound.messaging;

import dev.lmarchesoti.ordermanagement.adapter.producta.dto.ProductAOrder;
import dev.lmarchesoti.ordermanagement.adapter.producta.mapper.ProductAOrderMapper;
import dev.lmarchesoti.ordermanagement.application.command.InternalizeOrderCommand;
import dev.lmarchesoti.ordermanagement.application.port.in.ProcessOrderUseCase;
import dev.lmarchesoti.ordermanagement.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumerService {

    private final ProcessOrderUseCase processOrderUseCase;

    private final ProductAOrderMapper productAOrderMapper;

    @KafkaListener(topics = "${app.kafka.topic}")
    public void listenOrders(ProductAOrder message) {
        Order order = productAOrderMapper.toDomain(message);
        processOrderUseCase.processOrder(new InternalizeOrderCommand(order));
    }

}
