package com.first951.securitycompanyserver.schema.place;

import com.first951.securitycompanyserver.mapper.MappingType;
import com.first951.securitycompanyserver.schema.organization.OrganizationDto;
import com.first951.securitycompanyserver.schema.organization.OrganizationMapper;
import com.first951.securitycompanyserver.schema.organization.OrganizationService;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PlaceMapper {

    @Autowired
    protected OrganizationService organizationService;

    @Mapping(target = "organization", ignore = true)
    public abstract Place toEntity(PlaceDto dto,
                                   @Context MappingType mappingType);

    @AfterMapping
    public void toEntity(@MappingTarget Place entity, PlaceDto dto,
                         @Context MappingType mappingType) {
        if (dto.getOrganizationId() != null) {
            OrganizationMapper mapper = Mappers.getMapper(OrganizationMapper.class);
            try {
                OrganizationDto organizationDto = organizationService.read(dto.getOrganizationId());
                entity.setOrganization(mapper.toEntity(organizationDto, MappingType.DEFAULT));
            } catch (Exception e) {
                if (mappingType.equals(MappingType.DEFAULT)) {
                    throw e;
                }
                // Всё нормально, поле Organization останется null
            }
        }
    }

    public abstract List<Place> toEntityList(List<PlaceDto> dto,
                                             @Context MappingType mappingType);


    @Mapping(target = "organizationId", source = "entity.organization.id")
    public abstract PlaceDto toDto(Place entity);

    public abstract List<PlaceDto> toDtoList(List<Place> entity);

}
