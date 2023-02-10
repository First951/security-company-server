package com.first951.securitycompanyserver.object;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.object}")
@Tag(name = "Объект")
@RequiredArgsConstructor
public class ObjectController {

    private final ObjectService objectService;

    @GetMapping("{id}")
    @Operation(summary = "Получение объекть по id")
    public ObjectDto get(@PathVariable int id) {
        return objectService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка объектов")
    public List<ObjectDto> getAll() {
        return objectService.getAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового объекта")
    public ObjectDto post(@RequestBody ObjectDto objectDto) {
        return objectService.create(objectDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего объекта")
    public ObjectDto put(@RequestBody ObjectDto objectDto) {
        return objectService.update(objectDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление объекта")
    public ObjectDto delete(@PathVariable int id) {
        return objectService.delete(id);
    }
}
