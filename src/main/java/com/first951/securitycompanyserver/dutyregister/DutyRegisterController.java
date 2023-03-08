package com.first951.securitycompanyserver.dutyregister;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.dutyRegister}")
@Tag(name = "Регистр дежурств")
@RequiredArgsConstructor
public class DutyRegisterController {

    private final DutyRegisterService dutyRegisterService;

    @GetMapping("{id}")
    @Operation(summary = "Получение дежурства из регистра по id")
    public DutyRegisterDto get(@PathVariable int id) {
        return dutyRegisterService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка дежурств из регистра")
    public List<DutyRegisterDto> getAll() {
        return dutyRegisterService.getAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового дежурства в регистре")
    public DutyRegisterDto post(@RequestBody DutyRegisterDto dutyRegisterDto) {
        return dutyRegisterService.create(dutyRegisterDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего дежурства в регистре")
    public DutyRegisterDto put(@RequestBody DutyRegisterDto dutyRegisterDto) {
        return dutyRegisterService.update(dutyRegisterDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление дежурства в регистре")
    public DutyRegisterDto delete(@PathVariable int id) {
        return dutyRegisterService.delete(id);
    }

}
