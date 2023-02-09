package com.first951.securitycompanyserver.schedule;

import java.util.List;

public interface ScheduleService {

    ScheduleDto get(int id);

    List<ScheduleDto> getAll();

    ScheduleDto create(ScheduleDto scheduleDto);

    ScheduleDto update(int id, ScheduleDto scheduleDto);

    ScheduleDto deletePost(int id);

}
