package com.first951.securitycompanyserver.schema.place;

import com.first951.securitycompanyserver.mapper.MappingType;
import com.first951.securitycompanyserver.schema.organization.OrganizationDto;
import com.first951.securitycompanyserver.schema.organization.OrganizationMapper;
import com.first951.securitycompanyserver.schema.organization.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class PlaceMapper {

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private OrganizationMapper organizationMapper;

    @Mapping(target = "organization", ignore = true)
    public abstract Place toEntity(PlaceDto dto,
                                   @Context MappingType mappingType);

    @AfterMapping
    public void toEntity(@MappingTarget Place entity, PlaceDto dto,
                         @Context MappingType mappingType) {
        try {
            OrganizationDto organizationDto = organizationService.read(dto.getOrganizationId());
            entity.setOrganization(organizationMapper.toEntity(organizationDto, MappingType.DEFAULT));
        } catch (Exception e) {
            if ((mappingType.equals(MappingType.FORCE)) && (dto.getOrganizationId()) == null) {
                // Всё нормально, поле Organization останется null
            } else {
                throw e;
            }
        }
    }

    public abstract List<Place> toEntityList(List<PlaceDto> dto,
                                             @Context MappingType mappingType);


    @Mapping(target = "organizationId", source = "entity.organization.id")
    public abstract PlaceDto toDto(Place entity);

    public abstract List<PlaceDto> toDtoList(List<Place> entity);

}
