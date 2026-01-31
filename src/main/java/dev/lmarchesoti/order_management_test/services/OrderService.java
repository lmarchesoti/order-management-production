package dev.lmarchesoti.order_management_test.services;

import dev.lmarchesoti.order_management_test.entities.Order;
import dev.lmarchesoti.order_management_test.entities.OrderItem;
import dev.lmarchesoti.order_management_test.external.producta.dto.ProductAOrder;
import dev.lmarchesoti.order_management_test.external.producta.dto.ProductAOrderItem;
import dev.lmarchesoti.order_management_test.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final ShippingService shippingService;

    private final PricingService pricingService;

    private final OrderRepository orderRepository;

    @Transactional
    public void processOrder(ProductAOrder productAOrder) {

        Optional<Order> existingOrder = orderRepository.findByOrderId(productAOrder.orderId());

        Order order;

        if (existingOrder.isPresent()) {
            order = existingOrder.get();
            logger.info("Updating existing order with orderId: " + order.getOrderId());
        } else {
            order = Order.builder()
                    .orderId(productAOrder.orderId())
                    .customerId(productAOrder.customerId())
                    .status(productAOrder.status())
                    .build();
            logger.info("Creating new order with orderId: " + order.getOrderId());
        }

        List<ProductAOrderItem> productAOrderItems = productAOrder.orderItems();

        if (productAOrderItems != null && !productAOrderItems.isEmpty()) {

            for (ProductAOrderItem productAOrderItem : productAOrderItems) {

                OrderItem orderItem = OrderItem.builder()
                        .itemId(productAOrderItem.itemId())
                        .description(productAOrderItem.description())
                        .amount(productAOrderItem.amount())
                        .unitPrice(productAOrderItem.unitPrice())
                        .build();

                orderItem.setTotalPrice(pricingService.calculatePrice(orderItem));

                order.addOrderItem(orderItem);
            }

        }

        if (productAOrder.origin() != null) {
            order.setOrigin(productAOrder.origin().zipCode(), productAOrder.origin().number());
        }

        if (productAOrder.destination() != null) {
            order.setDestination(productAOrder.destination().zipCode(), productAOrder.destination().number());
        }

        if (order.hasCompleteShippingInfo()) {
            Double shippingCost = shippingService.calculateShipping(order.getShippingInfo());
            order.withShippingCost(shippingCost);
        }

        orderRepository.save(order);

    }

    public Page<Order> getOrders(Long customerId, LocalDateTime startCreatedAt, LocalDateTime endCreatedAt, Pageable pageRequest) {
        return orderRepository.findByFilters(customerId, startCreatedAt, endCreatedAt, pageRequest);
    }
}
