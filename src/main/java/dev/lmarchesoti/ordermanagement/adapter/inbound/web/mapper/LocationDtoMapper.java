package dev.lmarchesoti.ordermanagement.adapter.inbound.web.mapper;

import dev.lmarchesoti.ordermanagement.adapter.inbound.web.dto.LocationDto;
import dev.lmarchesoti.ordermanagement.domain.model.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationDtoMapper {

    LocationDto toDto(Location location);
}
