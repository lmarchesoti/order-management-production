package dev.lmarchesoti.ordermanagement.adapter.inbound.web.mapper;

import dev.lmarchesoti.ordermanagement.adapter.inbound.web.dto.OrderDto;
import dev.lmarchesoti.ordermanagement.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDtoMapper {

    @Mapping(source = "shippingInfo.origin", target = "origin")
    @Mapping(source = "shippingInfo.destination", target = "destination")
    OrderDto toDto(Order order);
}
