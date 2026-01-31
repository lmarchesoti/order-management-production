package dev.lmarchesoti.order_management_test.services;

import dev.lmarchesoti.order_management_test.entities.Address;
import dev.lmarchesoti.order_management_test.entities.Order;
import dev.lmarchesoti.order_management_test.entities.OrderItem;
import dev.lmarchesoti.order_management_test.external.producta.dto.ProductAOrder;
import dev.lmarchesoti.order_management_test.external.producta.dto.ProductAOrderItem;
import dev.lmarchesoti.order_management_test.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final AddressService addressService;

    private ShippingService shippingService;

    private PricingService pricingService;

    private OrderRepository orderRepository;

    public OrderService(AddressService addressService) {
        this.addressService = addressService;
    }

    @Transactional
    public void processOrder(ProductAOrder productAOrder) {
        logger.info("Processing order... " + productAOrder);

        if (orderRepository.existsByOrderId(productAOrder.orderId())) {
            return;
        }

        Order order = Order.builder()
                .orderId(productAOrder.orderId())
                .customerId(productAOrder.customerId())
                .status(productAOrder.status())
                .build();

        List<ProductAOrderItem> productAOrderItems = productAOrder.orderItems();

        if (productAOrderItems != null && !productAOrderItems.isEmpty()) {

            for (ProductAOrderItem productAOrderItem : productAOrderItems) {

                OrderItem orderItem = OrderItem.builder()
                        .itemId(productAOrderItem.itemId())
                        .amount(productAOrderItem.amount())
                        .unitPrice(productAOrderItem.unitPrice())
                        .build();

                orderItem.setTotalPrice(pricingService.calculatePrice(orderItem));

                order.addOrderItem(orderItem);
            }

        }

        if (productAOrder.origin() != null) {
            Address origin = addressService.getOrCreateAddress(productAOrder.origin());
            order.setOrigin(origin);
        }

        if (productAOrder.destination() != null) {
            Address destination = addressService.getOrCreateAddress(productAOrder.destination());
            order.setOrigin(destination);
        }

        if (order.getOrigin() != null && order.getDestination() != null) {
            Double shippingCost = shippingService.calculateShipping(order.getOrigin(), order.getDestination());
            order.withShippingCost(shippingCost);
        }

        orderRepository.save(order);

    }
}
