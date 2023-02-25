package com.sucks.socks_warehouse.services;

import com.sucks.socks_warehouse.model.socks.SocksBatch;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface StoreOperationService {

    void accept(SocksBatch socksBatch);

    void issuance(SocksBatch socksBatch);

    void reject(SocksBatch socksBatch);

    File exportFile() throws IOException;

    void importFile(MultipartFile file) throws IOException;
}
