package com.first951.securitycompanyserver.schema.organization;

import com.first951.securitycompanyserver.mapper.MappingType;
import com.first951.securitycompanyserver.schema.place.Place;
import com.first951.securitycompanyserver.schema.place.PlaceDto;
import com.first951.securitycompanyserver.schema.post.PostDto;
import com.first951.securitycompanyserver.schema.post.PostMapper;
import com.first951.securitycompanyserver.schema.post.PostService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

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
