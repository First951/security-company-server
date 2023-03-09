package com.first951.securitycompanyserver.schema.schedule;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}${application.endpoint.schedules}")
@Tag(name = "График дежурств")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService service;

    @GetMapping("{id}")
    @Operation(summary = "Получение элемента графика по id")
    public ScheduleDto get(@PathVariable long id) {
        return service.read(id);
    }

    @GetMapping("${application.endpoint.search}")
    @Operation(summary = "Поиск элемента графика")
    public List<ScheduleDto> search(ScheduleDto filter,
                                    @RequestParam(required = false) Long from,
                                    @RequestParam(required = false) Integer size) {
        return service.search(filter, from, size);
    }

    @PostMapping
    @Operation(summary = "Создание нового элемента графика")
    public ScheduleDto post(@RequestBody ScheduleDto scheduleDto) {
        return service.create(scheduleDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление существующего элемента графика")
    public ScheduleDto put(@PathVariable long id,
                           @RequestBody ScheduleDto scheduleDto) {
        return service.update(id, scheduleDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление элемента графика")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

}
