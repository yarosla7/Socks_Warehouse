package com.sucks.socks_warehouse.services.impl;

import com.sucks.socks_warehouse.model.operation.OperationType;
import com.sucks.socks_warehouse.model.operation.StoreOperation;
import com.sucks.socks_warehouse.model.socks.SocksBatch;
import com.sucks.socks_warehouse.services.FileService;
import com.sucks.socks_warehouse.services.StoreOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.asm.TypeReference;
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
    private final Path path = Path.of("src/main/resources", "socks-operations.json");

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
        return fileService.saveToFile(operationList, path).toFile();

    }

    @Override
    public void importFile(MultipartFile file) throws IOException {
        operationList = fileService.uploadFromFile(file, path, new TypeReference<List<StoreOperation>>() {
        });
    }
}