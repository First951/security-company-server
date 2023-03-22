package com.first951.securitycompanyserver.schema.place;

import com.first951.securitycompanyserver.mapper.MappingType;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class PlaceMapper {

    @Mapping(target = "posts", ignore = true)
    public abstract Place toEntity(PlaceDto dto);

    public abstract List<Place> toEntityList(List<PlaceDto> dto,
                                             @Context MappingType mappingType);


    @Mapping(target = "postDtos", ignore = true)
    public abstract PlaceDto toDto(Place entity);

    public abstract List<PlaceDto> toDtoList(List<Place> entity);

}
