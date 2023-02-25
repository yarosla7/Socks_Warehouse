package com.sucks.socks_warehouse.repository.impl;

import com.sucks.socks_warehouse.model.socks.Socks;
import com.sucks.socks_warehouse.model.socks.SocksBatch;
import com.sucks.socks_warehouse.repository.SocksRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SocksRepositoryImpl implements SocksRepository {
    private final HashMap<Socks, Integer> socksMap = new HashMap<>();

    @Override
    public void save(SocksBatch socksBatch) {
        Socks socks = socksBatch.getSocks();

        if (socksMap.containsKey(socks)) {
            socksMap.replace(socks, socksMap.get(socks) + socksBatch.getQuantity());
        } else {
            socksMap.put(socks, socksBatch.getQuantity());
        }
    }

    @Override
    public int remove(SocksBatch socksBatch) {
        Socks socks = socksBatch.getSocks();

        if (socksMap.containsKey(socks)) {
            int quantity = socksMap.get(socks);

            if (quantity > socksBatch.getQuantity()) {
                socksMap.replace(socks, socksMap.get(socks) - socksBatch.getQuantity());
                return socksBatch.getQuantity();
            } else {
                socksMap.remove(socks);
                return quantity;
            }
        }
        return 0;
    }

    @Override
    public Map<Socks, Integer> getAll() {
        return socksMap;
    }

    @Override
    public List<SocksBatch> getList() {
        List<SocksBatch> socksBatchList = new ArrayList<>();

        for (Map.Entry<Socks, Integer> socksItem : socksMap.entrySet()) {
            socksBatchList.add(new SocksBatch(socksItem.getKey(), socksItem.getValue()));
        }
        return socksBatchList;
    }

    @Override
    public void replace(List<SocksBatch> socksBatchList) {
        socksMap.clear();

        for (SocksBatch batch : socksBatchList) {
            save(batch);

        }
    }
}