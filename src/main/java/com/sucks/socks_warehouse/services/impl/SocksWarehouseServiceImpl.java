package com.sucks.socks_warehouse.services.impl;

import com.sucks.socks_warehouse.exception.ValidationException;
import com.sucks.socks_warehouse.model.Colors;
import com.sucks.socks_warehouse.model.Sizes;
import com.sucks.socks_warehouse.model.Socks;
import com.sucks.socks_warehouse.model.SocksBatch;
import com.sucks.socks_warehouse.repository.SocksRepository;
import com.sucks.socks_warehouse.services.SocksWarehouseService;
import com.sucks.socks_warehouse.services.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
@AllArgsConstructor
public class SocksWarehouseServiceImpl implements SocksWarehouseService {

    private final SocksRepository socksRepository;
    private final ValidationService validationService;

    @Override
    public void add(SocksBatch socksBatch) {
        try {
            checkForValidate(socksBatch);
        } catch (ValidationException e) {
            e.printStackTrace();
            throw new RuntimeException("Переданые параметры некорректны.");
        }
        socksRepository.save(socksBatch);
    }

    @Override
    public int edit(SocksBatch socksBatch) {
        try {
            checkForValidate(socksBatch);
        } catch (ValidationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return socksRepository.remove(socksBatch);
    }

    @Override
    public int getCount(Colors color, Sizes size, int cottonMin, int cottonMax) throws ValidationException {
        if (!validationService.validate(color, size, cottonMin, cottonMax)) {
            throw new ValidationException();
        }
        Map<Socks, Integer> socksIntegerMap = socksRepository.getAll();

        for (Map.Entry<Socks, Integer> socksItem : socksIntegerMap.entrySet()) {
            Socks socks = socksItem.getKey();

            if (socks.getColor().equals(color) &&
                    socks.getSize().equals(size) &&
                    socks.getCottonPart() >= cottonMin &&
                    socks.getCottonPart() <= cottonMax) {
                return socksItem.getValue();
            }
        }
        return 0;
    }

    private void checkForValidate(SocksBatch socksBatch) throws ValidationException {
        if (!validationService.validate(socksBatch)) {
            throw new ValidationException();
        }
    }
}