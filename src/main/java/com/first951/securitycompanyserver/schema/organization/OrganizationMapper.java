package com.first951.securitycompanyserver.schema.organization;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrganizationMapper {

    @Mapping(target = "posts", ignore = true)
    public abstract Organization toEntity(OrganizationDto dto);

    public abstract List<Organization> toEntityList(List<OrganizationDto> dto);


    @Mapping(target = "postDtos", ignore = true)
    public abstract OrganizationDto toDto(Organization entity);

    public abstract List<OrganizationDto> toDtoList(List<Organization> entity);

}
