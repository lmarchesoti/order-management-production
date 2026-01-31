package dev.lmarchesoti.order_management_test.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private Long orderId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    private Long customerId;

    private String status;

    @Getter
    @Setter
    @ManyToOne
    private Address origin;

    @Getter
    @Setter
    @ManyToOne
    private Address destination;

    private Integer totalItems;

    private Double totalPrice;

    private Double shippingCost;

    private Double totalPriceWithShipping;

    public void addOrderItem(OrderItem orderItem) {
        orderItem.setOrder(this);
        totalPrice += orderItem.getTotalPrice();
        totalItems += 1;
        orderItems.add(orderItem);
    }

    public void withShippingCost(Double value) {
        shippingCost = value;
        totalPriceWithShipping = totalPrice + shippingCost;
    }

}
