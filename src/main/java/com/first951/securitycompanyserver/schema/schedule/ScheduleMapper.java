package com.first951.securitycompanyserver.schema.schedule;

import com.first951.securitycompanyserver.mapper.MappingType;
import com.first951.securitycompanyserver.schema.post.PostMapper;
import com.first951.securitycompanyserver.schema.post.PostService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class ScheduleMapper {

    @Mapping(target = "post", ignore = true)
    public abstract Schedule toEntity(ScheduleDto dto,
                                      @Context MappingType mappingType);

    public abstract List<Schedule> toEntityList(List<ScheduleDto> dto,
                                                @Context MappingType mappingType);


    @Mapping(target = "postId", source = "entity.post.id")
    public abstract ScheduleDto toDto(Schedule entity);

    public abstract List<ScheduleDto> toDtoList(List<Schedule> entity);

}
