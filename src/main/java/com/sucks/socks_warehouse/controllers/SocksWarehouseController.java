package com.sucks.socks_warehouse.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * контроллер по CRUD операциям с носками
 */
@RestController
@RequestMapping("/api/socks")
@Tag(name = "СRUD - операции с носочками",
        description = "Принять носки на склад, отпустить носки, списать брак, проверить количество носков на складе")
public class SocksWarehouseController {
}