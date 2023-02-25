package com.sucks.socks_warehouse.services.impl;

import com.sucks.socks_warehouse.model.Colors;
import com.sucks.socks_warehouse.model.Sizes;
import com.sucks.socks_warehouse.model.SocksBatch;
import com.sucks.socks_warehouse.services.ValidationService;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validate(SocksBatch socksBatch) {
        return socksBatch.getSocks() != null &&
                socksBatch.getQuantity() > 0 &&
                socksBatch.getSocks().getColor() != null &&
                socksBatch.getSocks().getSize() != null &&
                cottonCheck(socksBatch.getSocks().getCottonPart(), socksBatch.getSocks().getCottonPart());
    }

    @Override
    public boolean validate(Colors color, Sizes size, int cottonMin, int cottonMax) {
        return color != null && size != null && cottonCheck(cottonMin, cottonMax);
    }

    private boolean cottonCheck(int cottonMin, int cottonMax) {
        return cottonMin >= 0 && cottonMax <= 100;
    }
}