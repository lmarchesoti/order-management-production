package dev.lmarchesoti.ordermanagement.adapter.inbound.web.mapper;

import dev.lmarchesoti.ordermanagement.adapter.inbound.web.dto.OrderItemDto;
import dev.lmarchesoti.ordermanagement.domain.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemDtoMapper {
    OrderItemDto toDto(OrderItem orderItem);
}
