package dev.lmarchesoti.ordermanagement.domain.service;

import dev.lmarchesoti.ordermanagement.domain.model.Order;
import dev.lmarchesoti.ordermanagement.domain.model.OrderItem;
import dev.lmarchesoti.ordermanagement.domain.model.ShippingInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final PricingService pricingService;

    private final ShippingService shippingService;

    public void calculateFields(Order order) {

        List<OrderItem> orderItems = order.getOrderItems();

        if (orderItems != null) {
            orderItems.forEach(item -> item.setTotalPrice(pricingService.calculatePrice(item)));

            order.setTotalItems(orderItems.size());

            Double totalPrice = orderItems.stream().map(OrderItem::getTotalPrice).reduce(0.0, Double::sum);
            order.setTotalPrice(totalPrice);
        }

        ShippingInfo shippingInfo = order.getShippingInfo();

        if (shippingInfo != null && shippingInfo.isComplete()) {
            Double shippingCost = shippingService.calculateShipping(order.getShippingInfo());
            order.addShippingCost(shippingCost);
        }
    }
}
