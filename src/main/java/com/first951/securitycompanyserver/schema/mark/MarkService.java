package com.first951.securitycompanyserver.schema.mark;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface MarkService {

    MarkDto create(@Valid MarkDto markDto);

    MarkDto read(@Positive long id);

    List<MarkDto> search(MarkDto filterDto, @PositiveOrZero Long from, @Positive Integer size);

    MarkDto update(@Positive long id, @Valid MarkDto markDto);

    void delete(@Positive long id);

}
