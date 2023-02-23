package com.sucks.socks_warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Партия определенных носков по модели
 */
@Data
@AllArgsConstructor
public class SocksBatch {
    private final Socks socks; //сами носочки
    private int quantity; //количество носков на складе
}
