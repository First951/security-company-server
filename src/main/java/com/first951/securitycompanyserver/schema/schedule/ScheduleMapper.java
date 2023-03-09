package com.first951.securitycompanyserver.schema.schedule;

import com.first951.securitycompanyserver.mapper.MappingType;
import com.first951.securitycompanyserver.schema.person.PersonDto;
import com.first951.securitycompanyserver.schema.person.PersonMapper;
import com.first951.securitycompanyserver.schema.person.PersonService;
import com.first951.securitycompanyserver.schema.post.PostDto;
import com.first951.securitycompanyserver.schema.post.PostMapper;
import com.first951.securitycompanyserver.schema.post.PostService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class ScheduleMapper {

    @Autowired
    private PostService postService;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonMapper personMapper;

    @Mapping(target = "post", ignore = true)
    @Mapping(target = "person", ignore = true)
    public abstract Schedule toEntity(ScheduleDto dto,
                                      @Context MappingType mappingType);

    @AfterMapping
    public void toEntity(@MappingTarget Schedule entity, ScheduleDto dto,
                         @Context MappingType mappingType) {
        try {
            PostDto postDto = postService.read(dto.getPostId());
            entity.setPost(postMapper.toEntity(postDto, MappingType.DEFAULT));
        } catch (Exception e) {
            if ((mappingType.equals(MappingType.FORCE)) && (dto.getPostId()) == null) {
                // Всё нормально, поле post останется null
            } else {
                throw e;
            }
        }

        try {
            PersonDto personDto = personService.read(dto.getPersonId());
            entity.setPerson(personMapper.toEntity(personDto, MappingType.DEFAULT));
        } catch (Exception e) {
            if ((mappingType.equals(MappingType.FORCE)) && (dto.getPersonId()) == null) {
                // Всё нормально, поле person останется null
            } else {
                throw e;
            }
        }
    }

    public abstract List<Schedule> toEntityList(List<ScheduleDto> dto,
                                                @Context MappingType mappingType);


    @Mapping(target = "postId", source = "entity.post.id")
    @Mapping(target = "personId", source = "entity.person.id")
    public abstract ScheduleDto toDto(Schedule entity);

    public abstract List<ScheduleDto> toDtoList(List<Schedule> entity);

}
