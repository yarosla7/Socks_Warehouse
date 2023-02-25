package com.sucks.socks_warehouse.repository.impl;

import com.sucks.socks_warehouse.model.Socks;
import com.sucks.socks_warehouse.model.SocksBatch;
import com.sucks.socks_warehouse.repository.SocksRepository;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.stereotype.Repository;
import org.webjars.NotFoundException;

import java.util.HashMap;
import java.util.Map;
@Repository
public class SocksRepositoryImpl implements SocksRepository {
    private HashMap<Socks, Integer> socksMap = new HashMap<>();

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
}
