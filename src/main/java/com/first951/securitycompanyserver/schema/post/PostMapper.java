package com.first951.securitycompanyserver.schema.post;

import com.first951.securitycompanyserver.mapper.MappingType;
import com.first951.securitycompanyserver.schema.place.PlaceDto;
import com.first951.securitycompanyserver.schema.place.PlaceMapper;
import com.first951.securitycompanyserver.schema.place.PlaceService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = PlaceMapper.class)
public abstract class PostMapper {

    @Autowired
    private PlaceService placeService;
    @Autowired
    private PlaceMapper placeMapper;

    @Mapping(target = "place", ignore = true)
    public abstract Post toEntity(PostDto dto,
                                  @Context MappingType mappingTyp);

    @AfterMapping
    public void toEntity(@MappingTarget Post entity, PostDto dto,
                         @Context MappingType mappingType) {
        try {
            PlaceDto placeDto = placeService.read(dto.getPlaceId());
            entity.setPlace(placeMapper.toEntity(placeDto, MappingType.DEFAULT));
        } catch (Exception e) {
            if (mappingType.equals(MappingType.FORCE)) {
                // Всё нормально, поле Organization останется null
            } else {
                throw e;
            }
        }
    }

    public abstract List<Post> toEntityList(List<PostDto> dto,
                                            @Context MappingType mappingType);


    @Mapping(target = "placeId", source = "entity.place.id")
    public abstract PostDto toDto(Post entity);

    public abstract List<PostDto> toDtoList(List<Post> entity);

}
