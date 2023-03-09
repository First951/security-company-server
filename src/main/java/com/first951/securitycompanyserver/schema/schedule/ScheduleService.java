package com.first951.securitycompanyserver.schema.schedule;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface ScheduleService {

    ScheduleDto create(@Valid ScheduleDto scheduleDto);

    ScheduleDto read(@Positive long id);

    List<ScheduleDto> search(ScheduleDto filterDto, @PositiveOrZero Long from, @Positive Integer size);

    ScheduleDto update(@Positive long id, @Valid ScheduleDto scheduleDto);

    void delete(@Positive long id);

}
