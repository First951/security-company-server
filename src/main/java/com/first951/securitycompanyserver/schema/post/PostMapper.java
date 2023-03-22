package com.first951.securitycompanyserver.schema.post;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Mapping(target = "place", ignore = true)
    @Mapping(target = "organization", ignore = true)
    public abstract Post toEntity(PostDto dto);

    public abstract List<Post> toEntityList(List<PostDto> dto);


    @Mapping(target = "placeId", source = "entity.place.id")
    @Mapping(target = "organizationId", source = "entity.organization.id")
    public abstract PostDto toDto(Post entity);

    public abstract List<PostDto> toDtoList(List<Post> entity);

}
