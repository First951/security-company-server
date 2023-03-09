package com.first951.securitycompanyserver.schema.mark;

import com.first951.securitycompanyserver.mapper.MappingType;
import com.first951.securitycompanyserver.schema.schedule.ScheduleDto;
import com.first951.securitycompanyserver.schema.schedule.ScheduleMapper;
import com.first951.securitycompanyserver.schema.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class MarkMapper {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ScheduleMapper scheduleMapper;

    @Mapping(target = "schedule", ignore = true)
    public abstract Mark toEntity(MarkDto dto,
                                  @Context MappingType mappingType);

    @AfterMapping
    public void toEntity(@MappingTarget Mark entity, MarkDto dto,
                         @Context MappingType mappingType) {
        try {
            ScheduleDto scheduleDto = scheduleService.read(dto.getScheduleId());
            entity.setSchedule(scheduleMapper.toEntity(scheduleDto, MappingType.DEFAULT));
        } catch (Exception e) {
            if ((mappingType.equals(MappingType.FORCE)) && (dto.getScheduleId()) == null) {
                // Всё нормально, поле organization останется null
            } else {
                throw e;
            }
        }
    }

    public abstract List<Mark> toEntityList(List<MarkDto> dto,
                                            @Context MappingType mappingType);


    @Mapping(target = "scheduleId", source = "entity.schedule.id")
    public abstract MarkDto toDto(Mark entity);

    public abstract List<MarkDto> toDtoList(List<Mark> entity);

}
