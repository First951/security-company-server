package com.first951.securitycompanyserver.schema.schedule;

import com.first951.securitycompanyserver.exception.BadRequestException;
import com.first951.securitycompanyserver.exception.ConflictException;
import com.first951.securitycompanyserver.exception.NotFoundException;
import com.first951.securitycompanyserver.page.OffsetBasedPage;
import com.first951.securitycompanyserver.schema.mark.MarkMapper;
import com.first951.securitycompanyserver.schema.person.PersonMapper;
import com.first951.securitycompanyserver.schema.post.PostDto;
import com.first951.securitycompanyserver.schema.post.PostMapper;
import com.first951.securitycompanyserver.schema.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final PostService postService;
    private final PostMapper postMapper;
    private final MarkMapper markMapper;
    private final PersonMapper personMapper;

    @Override
    public ScheduleDto create(ScheduleDto scheduleDto) {
        try {
            Schedule scheduleRequest = scheduleMapper.toEntity(scheduleDto);
            PostDto postDto = postService.read(scheduleDto.getPostId());
            scheduleRequest.setPost(postMapper.toEntity(postDto));

            Schedule scheduleResponse = scheduleRepository.save(scheduleRequest);
            return toDto(scheduleResponse);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Нарушение целостности данных");
        }
    }

    @Override
    public ScheduleDto read(long id) {
        Schedule scheduleResponse = scheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Элемент расписания с id=" + id + " не найден"));
        return toDto(scheduleResponse);
    }

    @Override
    public List<ScheduleDto> search(ScheduleDto filterDto, Long from, Integer size) {
        Schedule filter = scheduleMapper.toEntity(filterDto);
        Pageable pageable = new OffsetBasedPage(from, size);

        List<Schedule> schedules =
                scheduleRepository.search(filter.getPost(), filter.getBegin(), filter.getEnd(),
                        pageable);
        return toDtoList(schedules);
    }

    @Override
    public List<ScheduleDto> searchByDay(ScheduleDto filterDto, Date day, Long from, Integer size) {
        Schedule filter = scheduleMapper.toEntity(filterDto);
        Instant truncatedDay = day.toInstant().truncatedTo(ChronoUnit.HOURS);
        Instant truncatedNextDay = truncatedDay.plus(1, ChronoUnit.DAYS);

        Pageable pageable = new OffsetBasedPage(from, size);

        List<Schedule> schedules = scheduleRepository.search(filter.getPost(),
                Date.from(truncatedDay), Date.from(truncatedNextDay), pageable);
        return toDtoList(schedules);
    }

    @Override
    public ScheduleDto update(long id, ScheduleDto scheduleDto) {
        if (scheduleRepository.existsById(id)) {
            Schedule scheduleRequest = scheduleMapper.toEntity(scheduleDto);
            scheduleRequest.setId(id);
            PostDto postDto = postService.read(scheduleDto.getPostId());
            scheduleRequest.setPost(postMapper.toEntity(postDto));

            Schedule scheduleResponse = scheduleRepository.save(scheduleRequest);
            return toDto(scheduleResponse);
        } else {
            throw new NotFoundException("Элемент расписания с id=" + id + " не найден");
        }
    }

    @Override
    public void delete(long id) {
        if (scheduleRepository.existsById(id)) {
            try {
                scheduleRepository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new BadRequestException("Невозможно удалить элемент расписания с id= " + id + ". Нарушение целостности" +
                        " данных");
            }
        } else {
            throw new NotFoundException("Элемент расписания с id=" + id + " не найден");
        }
    }

    private ScheduleDto toDto(Schedule schedule) {
        ScheduleDto scheduleDto = scheduleMapper.toDto(schedule);
        scheduleDto.setPostDto(postService.read(schedule.getPost().getId()));
        scheduleDto.setMarkDtos(new ArrayList<>());
        schedule.getMarks().forEach(mark -> {
            scheduleDto.getMarkDtos().add(markMapper.toDto(mark));
            scheduleDto.getMarkDtos().get(scheduleDto.getMarkDtos().size() - 1).setPersonDto(personMapper.toDto(mark.getPerson()));
        });
        return scheduleDto;
    }

    private List<ScheduleDto> toDtoList(List<Schedule> schedules) {
        List<ScheduleDto> response = new ArrayList<>();
        schedules.forEach(schedule -> response.add(toDto(schedule)));
        return response;
    }

}
