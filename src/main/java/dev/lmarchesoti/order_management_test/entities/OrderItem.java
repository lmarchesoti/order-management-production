package dev.lmarchesoti.order_management_test.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "OrderItems")
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;

    private Long itemId;

    private String description;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Long amount;

    private Double unitPrice;

    private Double totalPrice;

}
