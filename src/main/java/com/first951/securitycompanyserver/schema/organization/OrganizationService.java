package com.first951.securitycompanyserver.schema.organization;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface OrganizationService {

    OrganizationDto create(@Valid OrganizationDto organizationDto);

    OrganizationDto read(@Positive long id);

    List<OrganizationDto> search(OrganizationDto organizationDto, @PositiveOrZero Long from,
                                 @Positive Integer size);

    OrganizationDto update(@Positive long id, @Valid OrganizationDto organizationDto);

    void delete(@Positive long id);

}
