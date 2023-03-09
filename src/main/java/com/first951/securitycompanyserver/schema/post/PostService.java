package com.first951.securitycompanyserver.schema.post;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface PostService {

    PostDto create(@Valid PostDto postDto);

    PostDto read(@Positive long id);

    List<PostDto> search(PostDto filterDto, @PositiveOrZero Long from, @Positive Integer size);

    PostDto update(@Positive long id, @Valid PostDto postDto);

    void delete(@Positive long id);

}
