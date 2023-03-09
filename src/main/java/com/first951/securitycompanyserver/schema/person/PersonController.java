package com.first951.securitycompanyserver.schema.person;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}${application.endpoint.persons}")
@Tag(name = "Человек")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @GetMapping("{id}")
    @Operation(summary = "Получение человека по id")
    public PersonDto get(@PathVariable long id) {
        return service.read(id);
    }

    @GetMapping("${application.endpoint.search}")
    @Operation(summary = "Поиск человека")
    public List<PersonDto> search(PersonDto filter,
                                  @RequestParam(required = false) Long from,
                                  @RequestParam(required = false) Integer size) {
        return service.search(filter, from, size);
    }

    @PostMapping
    @Operation(summary = "Создание нового человека")
    public PersonDto post(@RequestBody PersonDto personDto) {
        return service.create(personDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление существующего человека")
    public PersonDto put(@PathVariable long id,
                         @RequestBody PersonDto personDto) {
        return service.update(id, personDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление человека")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

}
