package com.first951.securitycompanyserver.schema.mark;

import com.first951.securitycompanyserver.mapper.MappingType;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class MarkMapper {

    @Mapping(target = "schedule", ignore = true)
    @Mapping(target = "person", ignore = true)
    public abstract Mark toEntity(MarkDto dto);

    public abstract List<Mark> toEntityList(List<MarkDto> dto,
                                            @Context MappingType mappingType);


    @Mapping(target = "scheduleId", source = "entity.schedule.id")
    @Mapping(target = "personId", source = "entity.person.id")
    public abstract MarkDto toDto(Mark entity);

    public abstract List<MarkDto> toDtoList(List<Mark> entity);

}
