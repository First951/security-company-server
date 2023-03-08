package com.first951.securitycompanyserver.organization;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrganizationMapper {

    Organization toEntity(OrganizationDto dto);

    OrganizationDto toDto(Organization entity);

    List<Organization> toEntityList(List<OrganizationDto> dto);

    List<OrganizationDto> toDtoList(List<Organization> entity);

}
