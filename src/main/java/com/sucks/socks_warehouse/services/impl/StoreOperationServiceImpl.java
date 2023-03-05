package com.sucks.socks_warehouse.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sucks.socks_warehouse.model.operation.OperationType;
import com.sucks.socks_warehouse.model.operation.StoreOperation;
import com.sucks.socks_warehouse.model.socks.SocksBatch;
import com.sucks.socks_warehouse.services.FileService;
import com.sucks.socks_warehouse.services.StoreOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreOperationServiceImpl implements StoreOperationService {

    private final FileService fileService;
    private List<StoreOperation> operationList = new ArrayList<>();

    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${name.of.data.file}")
    private String dataFileName;

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

    @Override
    public File exportFile() throws IOException {
        return fileService.saveToFile(operationList, Path.of(dataFilePath).resolve(dataFileName)).toFile();

    }

    @Override
    public void importFile(MultipartFile file) throws IOException {
        operationList = fileService.uploadFromFile(file, Path.of(dataFilePath).resolve(dataFileName), new TypeReference<List<StoreOperation>>() {
        });
    }
}