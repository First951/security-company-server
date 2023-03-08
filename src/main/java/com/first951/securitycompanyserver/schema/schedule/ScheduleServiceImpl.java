package com.first951.securitycompanyserver.schema.schedule;

import com.first951.securitycompanyserver.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ModelMapper modelMapper;

    @Override
    public ScheduleDto get(int id) {
        ScheduleEntity schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "id", String.valueOf(id)));

        return modelMapper.map(schedule, ScheduleDto.class);
    }

    @Override
    public List<ScheduleDto> getAll() {
        Iterable<ScheduleEntity> schedules = scheduleRepository.findAll();
        List<ScheduleDto> scheduleDtos = new ArrayList<>();

        schedules.forEach(schedule -> scheduleDtos.add(modelMapper.map(schedule, ScheduleDto.class)));
        return scheduleDtos;
    }

    @Override
    public ScheduleDto create(ScheduleDto scheduleDto) {
        ScheduleEntity schedule = modelMapper.map(scheduleDto, ScheduleEntity.class);

        ScheduleEntity createdSchedule = scheduleRepository.save(schedule);
        return modelMapper.map(createdSchedule, ScheduleDto.class);
    }

    @Override
    public ScheduleDto update(ScheduleDto scheduleDto) {
        ScheduleEntity scheduleRequest = modelMapper.map(scheduleDto, ScheduleEntity.class);
        ScheduleEntity schedule = scheduleRepository.findById(scheduleRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "id", String.valueOf(scheduleRequest.getId())));
        schedule.setPost(scheduleRequest.getPost());
        schedule.setBegin(scheduleRequest.getBegin());
        schedule.setEnd(scheduleRequest.getEnd());

        ScheduleEntity createdSchedule = scheduleRepository.save(schedule);
        return modelMapper.map(createdSchedule, ScheduleDto.class);
    }

    @Override
    public ScheduleDto delete(int id) {
        ScheduleEntity schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "id", String.valueOf(id)));

        scheduleRepository.delete(schedule);
        return modelMapper.map(schedule, ScheduleDto.class);
    }

}
