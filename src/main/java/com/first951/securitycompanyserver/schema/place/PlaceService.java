package com.first951.securitycompanyserver.schema.place;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface PlaceService {

    PlaceDto create(@Valid PlaceDto placeDto);

    PlaceDto read(@Positive long id);

    List<PlaceDto> searchByName(PlaceDto filterDto, @PositiveOrZero Long from, @Positive Integer size);

    PlaceDto update(@Positive long id, @Valid PlaceDto placeDto);

    void delete(@Positive long id);

}
