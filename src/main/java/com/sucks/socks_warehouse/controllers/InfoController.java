package com.sucks.socks_warehouse.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Отчет о запуске и README",
        description = "readme обязателен к прочтению!")
public class InfoController {
    @GetMapping
    @Operation(
            summary = "Сообщение о корректном запуске"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Всё ок."
    )
    public String start() {
        return "Веб-приложение по работе с носками запущенно.";
    }

    @GetMapping("/info")
    @Operation(
            summary = "Данный эндпоинт выдаёт информацию о том, что может это приложение."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Всё ок."
    )
    public String info() {
        return "Скоро здесь будет текст из readme.md.";
    }
}