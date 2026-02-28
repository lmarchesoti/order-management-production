package dev.lmarchesoti.ordermanagement.adapter.producta.mapper;

import dev.lmarchesoti.ordermanagement.adapter.producta.dto.ProductAAddress;
import dev.lmarchesoti.ordermanagement.domain.model.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductAAddressMapper {

    Location toDomain(ProductAAddress productAAddress);
}
