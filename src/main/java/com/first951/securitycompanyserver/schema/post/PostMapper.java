package com.first951.securitycompanyserver.schema.post;

import com.first951.securitycompanyserver.mapper.MappingType;
import com.first951.securitycompanyserver.schema.organization.OrganizationDto;
import com.first951.securitycompanyserver.schema.organization.OrganizationMapper;
import com.first951.securitycompanyserver.schema.organization.OrganizationService;
import com.first951.securitycompanyserver.schema.place.PlaceDto;
import com.first951.securitycompanyserver.schema.place.PlaceMapper;
import com.first951.securitycompanyserver.schema.place.PlaceService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Mapping(target = "place", ignore = true)
    @Mapping(target = "organization", ignore = true)
    public abstract Post toEntity(PostDto dto);

    public abstract List<Post> toEntityList(List<PostDto> dto);


    @Mapping(target = "placeId", ignore = true)
    @Mapping(target = "organizationId", ignore = true)
    public abstract PostDto toDto(Post entity);

    @AfterMapping
    protected void toDto(@MappingTarget PostDto dto, Post entity) {
//        PlaceDto placeDto = placeMapper.toDto(entity.getPlace());
//        dto.setPlaceDto(placeDto);
//
//        OrganizationDto organizationDto = organizationMapper.toDto(entity.getOrganization());
//        dto.setOrganizationDto(organizationDto);
    }

    public abstract List<PostDto> toDtoList(List<Post> entity);

}
