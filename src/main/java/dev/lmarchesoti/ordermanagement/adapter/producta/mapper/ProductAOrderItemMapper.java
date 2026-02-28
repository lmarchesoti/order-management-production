package dev.lmarchesoti.ordermanagement.adapter.producta.mapper;

import dev.lmarchesoti.ordermanagement.adapter.producta.dto.ProductAOrderItem;
import dev.lmarchesoti.ordermanagement.domain.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductAOrderItemMapper {
    OrderItem toDomain(ProductAOrderItem productAOrderItem);
}
