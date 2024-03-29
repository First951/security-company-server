package com.first951.securitycompanyserver.schema.schedule;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class ScheduleMapper {

    @Mapping(target = "post", ignore = true)
    @Mapping(target = "marks", ignore = true)
    public abstract Schedule toEntity(ScheduleDto dto);

    public abstract List<Schedule> toEntityList(List<ScheduleDto> dto);


    @Mapping(target = "postId", source = "entity.post.id")
    @Mapping(target = "postDto", ignore = true)
    @Mapping(target = "markDtos", ignore = true)
    public abstract ScheduleDto toDto(Schedule entity);

    public abstract List<ScheduleDto> toDtoList(List<Schedule> entity);

}
