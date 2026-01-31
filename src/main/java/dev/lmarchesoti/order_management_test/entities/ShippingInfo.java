package dev.lmarchesoti.order_management_test.entities;

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
public class ShippingInfo {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String originZipCode;

    private Long originNumber;

    private String destinationZipCode;

    private Long destinationNumber;

    public void setOrigin(String zipCode, Long number) {
        originZipCode = zipCode;
        originNumber = number;
    }

    public void setDestination(String zipCode, Long number) {
        destinationZipCode = zipCode;
        destinationNumber = number;
    }

    public boolean hasDestination() {
        return destinationZipCode != null && destinationNumber != null;
    }

    public boolean hasOrigin() {
        return originZipCode != null && originNumber != null;
    }

    public boolean isComplete() {
        return hasOrigin() && hasDestination();
    }

}
