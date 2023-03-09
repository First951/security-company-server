package com.first951.securitycompanyserver.schema.person;

import com.first951.securitycompanyserver.mapper.MappingType;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PersonMapper {

    public abstract Person toEntity(PersonDto dto,
                                    @Context MappingType mappingType);


    public abstract List<Person> toEntityList(List<PersonDto> dto,
                                              @Context MappingType mappingType);


    public abstract PersonDto toDto(Person entity);

    public abstract List<PersonDto> toDtoList(List<Person> entity);

}
