package dev.lmarchesoti.ordermanagement.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
        Long itemId;
        String description;
        Long amount;
        Double unitPrice;
        Double totalPrice;
}
