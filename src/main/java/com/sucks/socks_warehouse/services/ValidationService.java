package com.sucks.socks_warehouse.services;

import com.sucks.socks_warehouse.model.socks.Colors;
import com.sucks.socks_warehouse.model.socks.Sizes;
import com.sucks.socks_warehouse.model.socks.SocksBatch;

public interface ValidationService {

    boolean validate(SocksBatch socksBatch);

    boolean validate(Colors color, Sizes size, int countMon, int countMax);
}