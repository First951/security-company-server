package com.first951.securitycompanyserver.organization;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}${application.endpoint.organization}")
@Tag(name = "Организация")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService service;

    @GetMapping("{id}")
    @Operation(summary = "Получение организации по id")
    public OrganizationDto get(@PathVariable long id) {
        return service.read(id);
    }

    @GetMapping("${application.endpoint.search}")
    @Operation(summary = "Поиск организаций")
    public List<OrganizationDto> search(OrganizationDto filter,
                                        @RequestParam(required = false) Long from,
                                        @RequestParam(required = false) Integer size) {
        return service.search(filter, from, size);
    }

    @PostMapping
    @Operation(summary = "Создание новой организации")
    public OrganizationDto post(@RequestBody OrganizationDto organizationDto) {
        return service.create(organizationDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление существующей организации")
    public OrganizationDto put(@PathVariable long id,
                               @RequestBody OrganizationDto organizationDto) {
        return service.update(id, organizationDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление организации")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

}
