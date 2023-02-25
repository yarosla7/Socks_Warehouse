package com.sucks.socks_warehouse.services;

import com.sucks.socks_warehouse.exception.ValidationException;
import com.sucks.socks_warehouse.model.Colors;
import com.sucks.socks_warehouse.model.Sizes;
import com.sucks.socks_warehouse.model.SocksBatch;

public interface SocksWarehouseService {
    /**
     * добавляет модель носков и количество таких пар в карту
     * @param socksBatch - создание маодели носков и их количества
     */
    void add(SocksBatch socksBatch);

    /**
     * изменение количества пар на складе
     * @param socksBatch - передаётся пара носков для списания или выдачи со склада
     * @return - возвращает int количество пар
     */
    int edit(SocksBatch socksBatch);

    /**
     * метод получение пары носков исходя из параметров запроса (свойств объекта (носков))
     * @param color - цвет
     * @param size - размер
     * @param cottonMin - минамальное значение хлопка в составе
     * @param cottonMax - максимальное значение хлопка в составе
     * @return - возвращает количество пар таких носков на складе
     * @throws ValidationException - выбрасывает ошибку валидаци
     */
    int getCount(Colors color, Sizes size, int cottonMin, int cottonMax) throws ValidationException;
}