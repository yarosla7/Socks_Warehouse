package com.sucks.socks_warehouse.services;

import com.sucks.socks_warehouse.model.Colors;
import com.sucks.socks_warehouse.model.Sizes;
import com.sucks.socks_warehouse.model.SocksBatch;

public interface ValidationService {

    boolean validate(SocksBatch socksBatch);

    boolean validate(Colors color, Sizes size, int countMon, int countMax);
}