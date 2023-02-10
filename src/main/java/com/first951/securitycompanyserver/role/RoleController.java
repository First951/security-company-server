package com.first951.securitycompanyserver.role;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.role}")
@Tag(name = "Роли")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("{id}")
    @Operation(summary = "Получение роли по id")
    public RoleDto get(@PathVariable int id) {
        return roleService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка ролей")
    public List<RoleDto> getAll() {
        return roleService.getAll();
    }

    @PostMapping
    @Operation(summary = "Создание новой роли")
    public RoleDto post(@RequestBody RoleDto roleDto) {
        return roleService.create(roleDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующей роли")
    public RoleDto put(@RequestBody RoleDto roleDto) {
        return roleService.update(roleDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление роли")
    public RoleDto delete(@PathVariable int id) {
        return roleService.delete(id);
    }
}
