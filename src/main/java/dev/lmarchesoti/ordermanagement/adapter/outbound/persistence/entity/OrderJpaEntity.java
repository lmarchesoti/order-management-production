package dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
public class OrderJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long orderId;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemJpaEntity> orderItems = new ArrayList<>();

    private Long customerId;

    private String status;

    @OneToOne(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private ShippingInfoJpaEntity shippingInfo;

    private Integer totalItems = 0;

    private Double totalPrice = 0.0;

    private Double shippingCost = 0.0;

    private Double totalPriceWithShipping = 0.0;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
