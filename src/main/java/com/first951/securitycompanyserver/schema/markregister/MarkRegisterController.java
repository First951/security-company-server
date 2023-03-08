package com.first951.securitycompanyserver.schema.markregister;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}${application.endpoint.markRegisters}")
@Tag(name = "Регистр отметок")
@RequiredArgsConstructor
public class MarkRegisterController {

    private final MarkRegisterService markRegisterService;

    @GetMapping("{id}")
    @Operation(summary = "Получение отметки из регистра по id")
    public MarkRegisterDto get(@PathVariable int id) {
        return markRegisterService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка отметок из регистра")
    public List<MarkRegisterDto> getAll() {
        return markRegisterService.getAll();
    }

    @PostMapping
    @Operation(summary = "Создание новой отметки в регистре")
    public MarkRegisterDto post(@RequestBody MarkRegisterDto markRegisterDto) {
        return markRegisterService.create(markRegisterDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующей отметки в регистре")
    public MarkRegisterDto put(@RequestBody MarkRegisterDto markRegisterDto) {
        return markRegisterService.update(markRegisterDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление отметки в регистре")
    public MarkRegisterDto delete(@PathVariable int id) {
        return markRegisterService.delete(id);
    }

}
