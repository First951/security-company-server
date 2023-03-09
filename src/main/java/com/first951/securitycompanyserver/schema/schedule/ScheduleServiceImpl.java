package com.first951.securitycompanyserver.schema.schedule;

import com.first951.securitycompanyserver.exception.BadRequestException;
import com.first951.securitycompanyserver.exception.ConflictException;
import com.first951.securitycompanyserver.exception.NotFoundException;
import com.first951.securitycompanyserver.mapper.MappingType;
import com.first951.securitycompanyserver.page.OffsetBasedPage;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    @Override
    public ScheduleDto create(ScheduleDto scheduleDto) {
        try {
            Schedule scheduleRequest = scheduleMapper.toEntity(scheduleDto, MappingType.DEFAULT);
            Schedule scheduleResponse = scheduleRepository.save(scheduleRequest);
            return scheduleMapper.toDto(scheduleResponse);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Нарушение целостности данных");
        }
    }

    @Override
    public ScheduleDto read(long id) {
        Schedule scheduleResponse = scheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Организация с id=" + id + " не найдена"));
        return scheduleMapper.toDto(scheduleResponse);
    }

    @Override
    public List<ScheduleDto> search(ScheduleDto filterDto, Long from, Integer size) {
        Schedule filter = scheduleMapper.toEntity(filterDto, MappingType.FORCE);
        Pageable pageable = new OffsetBasedPage(from, size);

        List<Schedule> schedules =
                scheduleRepository.search(filter.getPost(), filter.getPerson(), filter.getBegin(), filter.getEnd(),
                        pageable);
        return scheduleMapper.toDtoList(schedules);
    }

    @Override
    public ScheduleDto update(long id, ScheduleDto scheduleDto) {
        if (scheduleRepository.existsById(id)) {
            Schedule scheduleRequest = scheduleMapper.toEntity(scheduleDto, MappingType.DEFAULT);
            scheduleRequest.setId(id);

            Schedule scheduleResponse = scheduleRepository.save(scheduleRequest);
            return scheduleMapper.toDto(scheduleResponse);
        } else {
            throw new NotFoundException("Организация с id=" + id + " не найдена");
        }
    }

    @Override
    public void delete(long id) {
        if (scheduleRepository.existsById(id)) {
            try {
                scheduleRepository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new BadRequestException("Невозможно удалить организацию с id= " + id + ". Нарушение целостности" +
                        " данных");
            }
        } else {
            throw new NotFoundException("Организация с id=" + id + " не найдена");
        }
    }

}
