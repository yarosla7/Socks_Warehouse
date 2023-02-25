package com.sucks.socks_warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Класс модели носков
 */
@Data
@AllArgsConstructor
public class Socks {
    private final Colors color; //цвет носков
    private final Sizes size; //размер носков
    private final Integer cottonPart; //процентное содержание хлопка в составе носков, целое число от 0 до 100

}