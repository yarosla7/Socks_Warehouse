package com.sucks.socks_warehouse.model.operation;

import com.sucks.socks_warehouse.model.socks.SocksBatch;

import java.time.LocalDateTime;

public class StoreOperation {

    private final LocalDateTime dateTime = LocalDateTime.now();
    private OperationType type;
    private SocksBatch socksBatch;
}