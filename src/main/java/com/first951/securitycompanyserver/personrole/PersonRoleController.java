package com.first951.securitycompanyserver.personrole;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.personRole}")
@Tag(name = "Связь Роль - Человек")
@RequiredArgsConstructor
public class PersonRoleController {

    private final PersonRoleService personRoleService;

    @GetMapping("{id}")
    @Operation(summary = "Получение связи Роль - Человек по id")
    public PersonRoleDto get(@PathVariable int id) {
        return personRoleService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка связей Роль - Человек")
    public List<PersonRoleDto> getAll() {
        return personRoleService.getAll();
    }

    @PostMapping
    @Operation(summary = "Создание новой связи Роль - Человек")
    public PersonRoleDto post(@RequestBody PersonRoleDto personRoleDto) {
        return personRoleService.create(personRoleDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующей связи Роль - Человек")
    public PersonRoleDto put(@RequestBody PersonRoleDto personRoleDto) {
        return personRoleService.update(personRoleDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление связи Роль - Человек")
    public PersonRoleDto delete(@PathVariable int id) {
        return personRoleService.delete(id);
    }
}
