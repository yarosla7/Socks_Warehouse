package com.sucks.socks_warehouse.model.socks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Партия определенных носков по модели
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocksBatch {
    private Socks socks; //сами носочки
    private int quantity; //количество носков на складе
}