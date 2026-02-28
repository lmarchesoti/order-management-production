package dev.lmarchesoti.ordermanagement.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingInfo {
        Location origin;
        Location destination;

        public boolean hasOrigin() {
                return origin != null && origin.zipCode() != null && origin.number() != null;
        }

        public boolean hasDestination() {
                return destination != null && destination.zipCode() != null && destination.number() != null;
        }

        public boolean isComplete() {
                return hasOrigin() && hasDestination();
        }
}
