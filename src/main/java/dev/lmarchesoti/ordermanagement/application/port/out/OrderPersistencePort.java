package dev.lmarchesoti.ordermanagement.application.port.out;

import dev.lmarchesoti.ordermanagement.application.query.ListOrdersQuery;
import dev.lmarchesoti.ordermanagement.domain.model.Order;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface OrderPersistencePort {
    Page<Order> listOrders(ListOrdersQuery listOrdersQuery);

    boolean orderExists(Long orderId);

    void updateOrderStatus(Long orderId, String status, LocalDateTime updatedAt);

    void create(Order order);
}
