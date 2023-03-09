package com.first951.securitycompanyserver.schema.organization;

import com.first951.securitycompanyserver.mapper.MappingType;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrganizationMapper {

    public abstract Organization toEntity(OrganizationDto dto,
                                          @Context MappingType mappingType);


    public abstract List<Organization> toEntityList(List<OrganizationDto> dto,
                                                    @Context MappingType mappingType);


    public abstract OrganizationDto toDto(Organization entity);

    public abstract List<OrganizationDto> toDtoList(List<Organization> entity);

}
