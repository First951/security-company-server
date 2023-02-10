package com.first951.securitycompanyserver.post;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.post}")
@Tag(name = "Посты")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("{id}")
    @Operation(summary = "Получение поста по id")
    public PostDto get(@PathVariable int id) {
        return postService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка постов")
    public List<PostDto> getAll() {
        return postService.getAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового поста")
    public PostDto post(@RequestBody PostDto postDto) {
        return postService.create(postDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего поста")
    public PostDto put(@RequestBody PostDto postDto) {
        return postService.update(postDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление поста")
    public PostDto delete(@PathVariable int id) {
        return postService.delete(id);
    }
}
