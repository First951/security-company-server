package com.first951.securitycompanyserver.schedule;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.schedule}")
@Tag(name = "График дежурств")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("{id}")
    @Operation(summary = "Получение графика дежурств по id")
    public ScheduleDto get(@PathVariable int id) {
        return scheduleService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка графиков дежурств")
    public List<ScheduleDto> getAll() {
        return scheduleService.getAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового графига дежурств")
    public ScheduleDto post(@RequestBody ScheduleDto scheduleDto) {
        return scheduleService.create(scheduleDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего графига дежурств")
    public ScheduleDto put(@RequestBody ScheduleDto scheduleDto) {
        return scheduleService.update(scheduleDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление графика дежурств")
    public ScheduleDto delete(@PathVariable int id) {
        return scheduleService.delete(id);
    }

}
