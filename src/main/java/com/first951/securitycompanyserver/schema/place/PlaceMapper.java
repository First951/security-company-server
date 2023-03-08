package com.first951.securitycompanyserver.schema.place;

import com.first951.securitycompanyserver.mapper.MappingType;
import com.first951.securitycompanyserver.schema.organization.OrganizationDto;
import com.first951.securitycompanyserver.schema.organization.OrganizationMapper;
import com.first951.securitycompanyserver.schema.organization.OrganizationService;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaceMapper {

    @Mapping(target = "organization", ignore = true)
    Place toEntity(PlaceDto dto,
                   @Context OrganizationService organizationService,
                   @Context MappingType mappingType);

    @Mapping(target = "organizationId", source = "entity.organization.id")
    PlaceDto toDto(Place entity);

    List<Place> toEntityList(List<PlaceDto> dto);

    List<PlaceDto> toDtoList(List<Place> entity);

    @AfterMapping
    default void toEntity(@MappingTarget Place entity, PlaceDto dto,
                          @Context OrganizationService organizationService,
                          @Context MappingType mappingType) {
        if (dto.getOrganizationId() != null) {
            OrganizationMapper mapper = Mappers.getMapper(OrganizationMapper.class);
            try {
                OrganizationDto organizationDto = organizationService.read(dto.getOrganizationId());
                entity.setOrganization(mapper.toEntity(organizationDto));
            } catch (Exception e) {
                if (mappingType.equals(MappingType.DEFAULT)) {
                    throw e;
                }
                // Всё нормально, поле Organization останется null
            }
        }
    }

}
