package dev.lmarchesoti.order_management_test.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private Long orderId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    private Long customerId;

    private String status;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private ShippingInfo shippingInfo = new ShippingInfo();

    private Integer totalItems;

    private Double totalPrice;

    private Double shippingCost;

    private Double totalPriceWithShipping;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

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

    public void setOrigin(String zipCode, Long number) {
        shippingInfo.setOriginZipCode(zipCode);
        shippingInfo.setOriginNumber(number);
    }

    public void setDestination(String zipCode, Long number) {
        shippingInfo.setDestinationZipCode(zipCode);
        shippingInfo.setDestinationNumber(number);
    }

    public boolean hasCompleteShippingInfo() {
        return shippingInfo != null && shippingInfo.isComplete();
    }

}
