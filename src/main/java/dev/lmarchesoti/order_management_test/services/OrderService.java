package dev.lmarchesoti.order_management_test.services;

import dev.lmarchesoti.order_management_test.entities.Order;
import dev.lmarchesoti.order_management_test.entities.OrderItem;
import dev.lmarchesoti.order_management_test.entities.ShippingInfo;
import dev.lmarchesoti.order_management_test.external.producta.dto.ProductAOrder;
import dev.lmarchesoti.order_management_test.external.producta.dto.ProductAOrderItem;
import dev.lmarchesoti.order_management_test.repositories.OrderRepository;
import dev.lmarchesoti.order_management_test.specifications.OrderSpecifications;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

        if (existingOrder.isPresent()) {
            logger.info("Updating order with orderId" + productAOrder.orderId());
            updateOrder(existingOrder.get(), productAOrder);
        } else {
            logger.info("Creating new order with orderId: " + productAOrder.orderId());
            Order order = createOrder(productAOrder);
            logger.info("Finished creating product with id: " + order.getId());
        }


    }

    protected void updateOrder(Order order, ProductAOrder productAOrder) {
        order.setStatus(productAOrder.status());
        orderRepository.save(order);
    }

    protected Order createOrder(ProductAOrder productAOrder) {
        Order order = new Order();
        order.setOrderId(productAOrder.orderId());
        order.setCustomerId(productAOrder.customerId());
        order.setStatus(productAOrder.status());
        order.setCreatedAt(productAOrder.createdAt());
        order.setUpdatedAt(productAOrder.updatedAt());

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

        if (productAOrder.origin() != null || productAOrder.destination() != null) {
            ShippingInfo shippingInfo = new ShippingInfo();
            shippingInfo.setOrder(order);

            if (productAOrder.origin() != null) {
                shippingInfo.setOrigin(productAOrder.origin().zipCode(), productAOrder.origin().number());
            }

            if (productAOrder.destination() != null) {
                shippingInfo.setDestination(productAOrder.destination().zipCode(), productAOrder.destination().number());
            }

            order.setShippingInfo(shippingInfo);

            if (shippingInfo.isComplete()) {
                Double shippingCost = shippingService.calculateShipping(order.getShippingInfo());
                order.withShippingCost(shippingCost);
            }
        }

        orderRepository.save(order);
        return order;
    }

    public Page<Order> getOrders(Long customerId, LocalDateTime startCreatedAt, LocalDateTime endCreatedAt, Pageable pageable) {
        Specification<Order> specification = Specification.allOf(
                OrderSpecifications.hasCustomerId(customerId),
                OrderSpecifications.createdAfter(startCreatedAt),
                OrderSpecifications.createdBefore(endCreatedAt)
        );

        return orderRepository.findAll(specification, pageable);
    }
}
