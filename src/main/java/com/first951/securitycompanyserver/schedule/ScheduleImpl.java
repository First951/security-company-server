package com.first951.securitycompanyserver.schedule;

import com.first951.securitycompanyserver.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ModelMapper modelMapper;

    public ScheduleImpl(ScheduleRepository scheduleRepository, ModelMapper modelMapper) {
        this.scheduleRepository = scheduleRepository;
        this.modelMapper = modelMapper;
    }


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

        schedules.forEach(schedule -> modelMapper.map(schedule, ScheduleDto.class));
        return scheduleDtos;
    }

    @Override
    public ScheduleDto create(ScheduleDto scheduleDto) {
        ScheduleEntity schedule = modelMapper.map(scheduleDto, ScheduleEntity.class);

        ScheduleEntity createdSchedule = scheduleRepository.save(schedule);
        return modelMapper.map(createdSchedule, ScheduleDto.class);
    }

    @Override
    public ScheduleDto update(int id, ScheduleDto scheduleDto) {
        ScheduleEntity scheduleRequest = modelMapper.map(scheduleDto, ScheduleEntity.class);
        ScheduleEntity schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "id", String.valueOf(id)));
        schedule.setPost(scheduleRequest.getPost());
        schedule.setBegin(scheduleRequest.getBegin());
        schedule.setEnd(scheduleRequest.getEnd());

        ScheduleEntity createdSchedule = scheduleRepository.save(schedule);
        return modelMapper.map(createdSchedule, ScheduleDto.class);
    }

    @Override
    public ScheduleDto deletePost(int id) {
        ScheduleEntity schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "id", String.valueOf(id)));

        scheduleRepository.delete(schedule);
        return modelMapper.map(schedule, ScheduleDto.class);
    }
}
