package dev.lmarchesoti.ordermanagement.application.port.in;

import dev.lmarchesoti.ordermanagement.application.query.ListOrdersQuery;
import dev.lmarchesoti.ordermanagement.domain.model.Order;
import org.springframework.data.domain.Page;

public interface ListOrdersUseCase {
    Page<Order> listOrders(ListOrdersQuery listOrdersQuery);
}
