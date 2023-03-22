package com.first951.securitycompanyserver.schema.place;

import com.first951.securitycompanyserver.mapper.MappingType;
import com.first951.securitycompanyserver.schema.post.PostDto;
import com.first951.securitycompanyserver.schema.post.PostMapper;
import com.first951.securitycompanyserver.schema.post.PostService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class PlaceMapper {

    @Mapping(target = "posts", ignore = true)
    public abstract Place toEntity(PlaceDto dto);

    public abstract List<Place> toEntityList(List<PlaceDto> dto,
                                             @Context MappingType mappingType);


    @Mapping(target = "postDtos", ignore = true)
    public abstract PlaceDto toDto(Place entity);

    public abstract List<PlaceDto> toDtoList(List<Place> entity);

}
