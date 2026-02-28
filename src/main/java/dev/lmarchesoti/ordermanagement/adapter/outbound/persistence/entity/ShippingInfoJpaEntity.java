package dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ShippingInfo")
public class ShippingInfoJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderJpaEntity order;

    private String originZipCode;

    private Long originNumber;

    private String destinationZipCode;

    private Long destinationNumber;

}
