package com.first951.securitycompanyserver.schema.mark;

import com.first951.securitycompanyserver.exception.BadRequestException;
import com.first951.securitycompanyserver.exception.ConflictException;
import com.first951.securitycompanyserver.exception.NotFoundException;
import com.first951.securitycompanyserver.page.OffsetBasedPage;
import com.first951.securitycompanyserver.schema.person.PersonDto;
import com.first951.securitycompanyserver.schema.person.PersonMapper;
import com.first951.securitycompanyserver.schema.person.PersonService;
import com.first951.securitycompanyserver.schema.schedule.ScheduleDto;
import com.first951.securitycompanyserver.schema.schedule.ScheduleMapper;
import com.first951.securitycompanyserver.schema.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarkServiceImpl implements MarkService {

    private final MarkRepository markRepository;
    private final MarkMapper markMapper;
    private final ScheduleMapper scheduleMapper;
    private final PersonMapper personMapper;
    private final ScheduleService scheduleService;
    private final PersonService personService;

    @Override
    public MarkDto create(MarkDto markDto) {
        try {
            Mark markRequest = markMapper.toEntity(markDto);
            ScheduleDto scheduleDto = scheduleService.read(markDto.getScheduleId());
            markRequest.setSchedule(scheduleMapper.toEntity(scheduleDto));
            if (markDto.getPersonId() != null) {
                PersonDto personDto = personService.read(markDto.getPersonId());
                markRequest.setPerson(personMapper.toEntity(personDto));
            }

            Mark markResponse = markRepository.save(markRequest);
            return toDto(markResponse);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Нарушение целостности данных");
        }
    }

    @Override
    public MarkDto read(long id) {
        Mark markResponse = markRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Отметка с id=" + id + " не найдена"));
        return toDto(markResponse);
    }

    @Override
    public List<MarkDto> search(MarkDto filterDto, Long from, Integer size) {
        Mark filter = markMapper.toEntity(filterDto);
        Pageable pageable = new OffsetBasedPage(from, size);

        List<Mark> marks = markRepository.search(filter.getSchedule(), filter.getPlanTimestamp(),
                filter.getFactTimestamp(), filter.getType(), filter.getComment(), pageable);
        return toDtoList(marks);
    }

    @Override
    public MarkDto update(long id, MarkDto markDto) {
        if (markRepository.existsById(id)) {
            Mark markRequest = markMapper.toEntity(markDto);
            markRequest.setId(id);
            ScheduleDto scheduleDto = scheduleService.read(markDto.getScheduleId());
            markRequest.setSchedule(scheduleMapper.toEntity(scheduleDto));
            if (markDto.getPersonId() != null) {
                PersonDto personDto = personService.read(markDto.getPersonId());
                markRequest.setPerson(personMapper.toEntity(personDto));
            }

            Mark markResponse = markRepository.save(markRequest);
            return toDto(markResponse);
        } else {
            throw new NotFoundException("Отметка с id=" + id + " не найдена");
        }
    }

    @Override
    public void delete(long id) {
        if (markRepository.existsById(id)) {
            try {
                markRepository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new BadRequestException("Невозможно удалить отметку с id= " + id + ". Нарушение целостности" +
                        " данных");
            }
        } else {
            throw new NotFoundException("Отметка с id=" + id + " не найдена");
        }
    }

    private MarkDto toDto(Mark mark) {
        MarkDto markDto = markMapper.toDto(mark);
        markDto.setScheduleDto(scheduleMapper.toDto(mark.getSchedule()));
        markDto.setPersonDto(personMapper.toDto(mark.getPerson()));
        return markDto;
    }

    private List<MarkDto> toDtoList(List<Mark> marks) {
        List<MarkDto> response = new ArrayList<>();
        marks.forEach(mark -> response.add(toDto(mark)));
        return response;
    }

}
