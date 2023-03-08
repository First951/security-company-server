package com.first951.securitycompanyserver.schema.markplan;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${application.endpoint.root}${application.endpoint.markPlans}")
@Tag(name = "План отметок")
@RequiredArgsConstructor
public class MarkPlanController {

//    private final RoleService roleService;
//
//    @GetMapping("{id}")
//    @Operation(summary = "Получение плановой отметки по id")
//    public RoleDto get(@PathVariable int id) {
//        return roleService.get(id);
//    }
//
//    @GetMapping
//    @Operation(summary = "Получение списка плановых отметок")
//    public List<RoleDto> getAll() {
//        return roleService.getAll();
//    }
//
//    @PostMapping
//    @Operation(summary = "Создание новой плановой отметки")
//    public RoleDto post(@RequestBody RoleDto roleDto) {
//        return roleService.create(roleDto);
//    }
//
//    @PutMapping
//    @Operation(summary = "Обновление существующей плановой отметки")
//    public RoleDto put(@RequestBody RoleDto roleDto) {
//        return roleService.update(roleDto);
//    }
//
//    @DeleteMapping("{id}")
//    @Operation(summary = "Удаление плановой отметки")
//    public RoleDto delete(@PathVariable int id) {
//        return roleService.delete(id);
//    }

}
