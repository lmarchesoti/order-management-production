package dev.lmarchesoti.ordermanagement.adapter.producta.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ProductAOrder(Long orderId,
                            Long customerId,
                            String status,
                            LocalDateTime createdAt,
                            LocalDateTime updatedAt,
                            ProductAAddress origin,
                            ProductAAddress destination,
                            List<ProductAOrderItem> orderItems) {
}
