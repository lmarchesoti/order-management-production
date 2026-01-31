package dev.lmarchesoti.order_management_test.dto;

import dev.lmarchesoti.order_management_test.entities.Order;
import dev.lmarchesoti.order_management_test.entities.ShippingInfo;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(
        Long orderId,
        Long customerId,
        String status,
        LocationDto origin,
        LocationDto destination,
        Integer totalItems,
        Double totalPrice,
        Double shippingCost,
        Double totalPriceWithShipping,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<OrderItemDto> orderItems) {

    public static OrderDto fromOrder(Order order) {
        ShippingInfo shippingInfo = order.getShippingInfo();
        LocationDto origin = null;
        LocationDto destination = null;

        if (shippingInfo != null) {
            if (shippingInfo.hasOrigin()) {
                origin = new LocationDto(shippingInfo.getOriginZipCode(), shippingInfo.getOriginNumber());
            }

            if (shippingInfo.hasDestination()) {
                destination = new LocationDto(shippingInfo.getDestinationZipCode(), shippingInfo.getDestinationNumber());
            }
        }

        return new OrderDto(order.getOrderId(),
                order.getCustomerId(),
                order.getStatus(),
                origin,
                destination,
                order.getTotalItems(),
                order.getTotalPrice(),
                order.getShippingCost(),
                order.getTotalPriceWithShipping(),
                order.getCreatedAt(),
                order.getUpdatedAt(),
                order.getOrderItems().stream().map(OrderItemDto::new).toList());
    }
}
