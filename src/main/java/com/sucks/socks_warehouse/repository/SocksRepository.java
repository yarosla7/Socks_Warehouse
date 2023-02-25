package com.sucks.socks_warehouse.repository;

import com.sucks.socks_warehouse.model.Socks;
import com.sucks.socks_warehouse.model.SocksBatch;

import java.util.Map;

public interface SocksRepository {

    /**
     * добавление в карту (на склад)
     * @param socksBatch - передаются параметры модели носков их количество
     */
    void save(SocksBatch socksBatch);

    /**
     * удаление из карты (со склада)
     * @param socksBatch - передаются параметры модели носков их количество
     * @return -
     */
    int remove(SocksBatch socksBatch);

    Map<Socks, Integer> getAll();
}
