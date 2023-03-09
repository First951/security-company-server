package com.first951.securitycompanyserver.schema.place;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}${application.endpoint.places}")
@Tag(name = "Объект")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService service;

    @GetMapping("{id}")
    @Operation(summary = "Получение объекта по id")
    public PlaceDto get(@PathVariable long id) {
        return service.read(id);
    }

    @GetMapping("${application.endpoint.search}")
    @Operation(summary = "Поиск объекта")
    public List<PlaceDto> search(PlaceDto filter,
                                 @RequestParam(required = false) Long from,
                                 @RequestParam(required = false) Integer size) {
        return service.search(filter, from, size);
    }

    @PostMapping
    @Operation(summary = "Создание нового объекта")
    public PlaceDto post(@RequestBody PlaceDto placeDto) {
        return service.create(placeDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление существующего объекта")
    public PlaceDto put(@PathVariable long id,
                        @RequestBody PlaceDto placeDto) {
        return service.update(id, placeDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление объекта")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

}
