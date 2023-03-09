package com.first951.securitycompanyserver.schema.person;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface PersonService {

    PersonDto create(@Valid PersonDto personDto);

    PersonDto read(@Positive long id);

    List<PersonDto> search(PersonDto personDto, @PositiveOrZero Long from,
                           @Positive Integer size);

    PersonDto update(@Positive long id, @Valid PersonDto personDto);

    void delete(@Positive long id);

}
