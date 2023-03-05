package com.sucks.socks_warehouse.model.operation;

import com.sucks.socks_warehouse.model.socks.SocksBatch;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class StoreOperation {

    private final LocalDateTime dateTime = LocalDateTime.now();
    private OperationType type;
    private SocksBatch socksBatch;
}