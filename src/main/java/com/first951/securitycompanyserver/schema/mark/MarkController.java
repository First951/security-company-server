package com.first951.securitycompanyserver.schema.mark;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}${application.endpoint.marks}")
@Tag(name = "Отметки")
@RequiredArgsConstructor
public class MarkController {

    private final MarkService service;

    @GetMapping("{id}")
    @Operation(summary = "Получение отметки по id")
    public MarkDto get(@PathVariable long id) {
        return service.read(id);
    }

    @GetMapping("${application.endpoint.search}")
    @Operation(summary = "Поиск отметки")
    public List<MarkDto> search(MarkDto filter,
                                @RequestParam(required = false) Long from,
                                @RequestParam(required = false) Integer size) {
        return service.search(filter, from, size);
    }

    @PostMapping
    @Operation(summary = "Создание новой отметки")
    public MarkDto post(@RequestBody MarkDto markDto) {
        return service.create(markDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление существующей отметки")
    public MarkDto put(@PathVariable long id,
                       @RequestBody MarkDto markDto) {
        return service.update(id, markDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление отметки")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

}
