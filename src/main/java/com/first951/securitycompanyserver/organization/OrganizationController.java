package com.first951.securitycompanyserver.organization;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.organization}")
@Tag(name = "Организация")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping("{id}")
    @Operation(summary = "Получение организации по id")
    public OrganizationDto get(@PathVariable int id) {
        return organizationService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка организаций")
    public List<OrganizationDto> getAll() {
        return organizationService.getAll();
    }

    @PostMapping
    @Operation(summary = "Создание новой организации")
    public OrganizationDto post(@RequestBody OrganizationDto organizationDto) {
        return organizationService.create(organizationDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующей организации")
    public OrganizationDto put(@RequestBody OrganizationDto organizationDto) {
        return organizationService.update(organizationDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление организации")
    public OrganizationDto delete(@PathVariable int id) {
        return organizationService.delete(id);
    }
}
