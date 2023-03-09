package com.first951.securitycompanyserver.schema.mark;

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
public class MarkServiceImpl implements MarkService {

    private final MarkRepository markRepository;
    private final MarkMapper markMapper;

    @Override
    public MarkDto create(MarkDto markDto) {
        try {
            Mark markRequest = markMapper.toEntity(markDto, MappingType.DEFAULT);
            Mark markResponse = markRepository.save(markRequest);
            return markMapper.toDto(markResponse);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Нарушение целостности данных");
        }
    }

    @Override
    public MarkDto read(long id) {
        Mark markResponse = markRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Отметка с id=" + id + " не найдена"));
        return markMapper.toDto(markResponse);
    }

    @Override
    public List<MarkDto> search(MarkDto filterDto, Long from, Integer size) {
        Mark filter = markMapper.toEntity(filterDto, MappingType.FORCE);
        Pageable pageable = new OffsetBasedPage(from, size);

        List<Mark> marks = markRepository.search(filter.getSchedule(), filter.getPlanTimestamp(),
                filter.getFactTimestamp(), filter.getType(), filter.getComment(), pageable);
        return markMapper.toDtoList(marks);
    }

    @Override
    public MarkDto update(long id, MarkDto markDto) {
        if (markRepository.existsById(id)) {
            Mark markRequest = markMapper.toEntity(markDto, MappingType.DEFAULT);
            markRequest.setId(id);

            Mark markResponse = markRepository.save(markRequest);
            return markMapper.toDto(markResponse);
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

}
