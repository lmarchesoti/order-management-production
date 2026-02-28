package dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.mapper;

import dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.entity.OrderItemJpaEntity;
import dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.entity.ShippingInfoJpaEntity;
import dev.lmarchesoti.ordermanagement.domain.model.Location;
import dev.lmarchesoti.ordermanagement.domain.model.OrderItem;
import dev.lmarchesoti.ordermanagement.domain.model.ShippingInfo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShippingInfoMapper {

    @Mapping(source = "originZipCode", target = "origin.zipCode")
    @Mapping(source = "originNumber", target = "origin.number")
    @Mapping(source = "destinationZipCode", target = "destination.zipCode")
    @Mapping(source = "destinationNumber", target = "destination.number")
    ShippingInfo toDomain(ShippingInfoJpaEntity jpaEntity);

    @InheritInverseConfiguration
    ShippingInfoJpaEntity toEntity(ShippingInfo shippingInfo);

}
