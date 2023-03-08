package com.first951.securitycompanyserver.person;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.person}")
@Tag(name = "Человек")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("{id}")
    @Operation(summary = "Получение человека по id")
    public PersonDto get(@PathVariable int id) {
        return personService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка людей")
    public List<PersonDto> getAll() {
        return personService.getAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового человека")
    public PersonDto post(@RequestBody PersonDto personDto) {
        return personService.create(personDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего человека")
    public PersonDto put(@RequestBody PersonDto personDto) {
        return personService.update(personDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление человека")
    public PersonDto delete(@PathVariable int id) {
        return personService.delete(id);
    }

}
