package com.sucks.socks_warehouse.services.impl;

import com.sucks.socks_warehouse.model.operation.OperationType;
import com.sucks.socks_warehouse.model.operation.StoreOperation;
import com.sucks.socks_warehouse.model.socks.Colors;
import com.sucks.socks_warehouse.model.socks.Sizes;
import com.sucks.socks_warehouse.model.socks.SocksBatch;
import com.sucks.socks_warehouse.services.StoreOperationService;
import com.sucks.socks_warehouse.services.ValidationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Service
    public static class StoreOperationServiceImpl implements StoreOperationService {

        private final List<StoreOperation> operationList = new ArrayList<>();

        @Override
        public void accept(SocksBatch socksBatch) {
            operationList.add(new StoreOperation(OperationType.ACCEPT, socksBatch));

        }

        @Override
        public void issuance(SocksBatch socksBatch) {
            operationList.add(new StoreOperation(OperationType.ISSUANCE, socksBatch));
        }

        @Override
        public void reject(SocksBatch socksBatch) {
            operationList.add(new StoreOperation(OperationType.REJECT, socksBatch));
        }
    }
}