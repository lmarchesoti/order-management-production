package dev.lmarchesoti.ordermanagement.application.service;

import dev.lmarchesoti.ordermanagement.application.command.InternalizeOrderCommand;
import dev.lmarchesoti.ordermanagement.application.port.in.ListOrdersUseCase;
import dev.lmarchesoti.ordermanagement.application.port.in.ProcessOrderUseCase;
import dev.lmarchesoti.ordermanagement.application.port.out.OrderPersistencePort;
import dev.lmarchesoti.ordermanagement.application.query.ListOrdersQuery;
import dev.lmarchesoti.ordermanagement.domain.model.Order;
import dev.lmarchesoti.ordermanagement.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrdersApplicationService implements ListOrdersUseCase, ProcessOrderUseCase {

    private static final Logger logger = LoggerFactory.getLogger(OrdersApplicationService.class);

    private final OrderService orderService;

    private final OrderPersistencePort orderPersistencePort;

    @Override
    public Page<Order> listOrders(ListOrdersQuery listOrdersQuery) {
        return orderPersistencePort.listOrders(listOrdersQuery);
    }

    @Override
    @Transactional
    public void processOrder(InternalizeOrderCommand command) {
        Order order = command.order();

        if (orderPersistencePort.orderExists(order.getOrderId())) {
            logger.info("Updating order status: { orderId: {}, status: {} }", order.getOrderId(), order.getStatus());
            orderPersistencePort.updateOrderStatus(order.getOrderId(), order.getStatus(), order.getUpdatedAt());
        } else {
            logger.info("Creating new order with orderId {}", order.getOrderId());
            orderService.calculateFields(order);
            orderPersistencePort.create(order);
        }
    }

}
