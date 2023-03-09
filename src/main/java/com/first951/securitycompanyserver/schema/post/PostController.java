package com.first951.securitycompanyserver.schema.post;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}${application.endpoint.posts}")
@Tag(name = "Посты")
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @GetMapping("{id}")
    @Operation(summary = "Получение поста по id")
    public PostDto get(@PathVariable long id) {
        return service.read(id);
    }

    @GetMapping("${application.endpoint.search}")
    @Operation(summary = "Поиск поста по имени")
    public List<PostDto> search(PostDto filter,
                                @RequestParam(required = false) Long from,
                                @RequestParam(required = false) Integer size) {
        return service.search(filter, from, size);
    }

    @PostMapping
    @Operation(summary = "Создание нового поста")
    public PostDto post(@RequestBody PostDto postDto) {
        return service.create(postDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление существующего поста")
    public PostDto put(@PathVariable long id,
                       @RequestBody PostDto postDto) {
        return service.update(id, postDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление поста")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

}
