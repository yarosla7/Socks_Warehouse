package com.sucks.socks_warehouse.model;

import lombok.Data;

/**
 * Класс модели носков
 */
@Data
public class Socks {
    private final Colors color; //цвет носков
    private final Sizes size; //размер носков
    private final Integer cottonPart; //процентное содержание хлопка в составе носков, целое число от 0 до 100

    public Socks(Colors color, Sizes size, Integer cottonPart, int quantity) {
        this.color = color;
        this.size = size;
        if (cottonPart >= 0 && cottonPart <= 100) {
            this.cottonPart = cottonPart;
        } else {
            this.cottonPart = 80; // дефолтное значение если процент хлопка будет введен свыше заданных границ
        }
    }
}