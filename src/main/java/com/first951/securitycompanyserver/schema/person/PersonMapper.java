package com.first951.securitycompanyserver.schema.person;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PersonMapper {

    public abstract Person toEntity(PersonDto dto);


    public abstract List<Person> toEntityList(List<PersonDto> dto);


    public abstract PersonDto toDto(Person entity);

    public abstract List<PersonDto> toDtoList(List<Person> entity);

}
