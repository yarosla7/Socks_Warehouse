package com.sucks.socks_warehouse.services.impl;

import com.sucks.socks_warehouse.exception.ValidationException;
import com.sucks.socks_warehouse.model.socks.Colors;
import com.sucks.socks_warehouse.model.socks.Sizes;
import com.sucks.socks_warehouse.model.socks.Socks;
import com.sucks.socks_warehouse.model.socks.SocksBatch;
import com.sucks.socks_warehouse.repository.SocksRepository;
import com.sucks.socks_warehouse.services.FileService;
import com.sucks.socks_warehouse.services.SocksWarehouseService;
import com.sucks.socks_warehouse.services.StoreOperationService;
import com.sucks.socks_warehouse.services.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class SocksWarehouseServiceImpl implements SocksWarehouseService {

    private final SocksRepository socksRepository;
    private final ValidationService validationService;
    private final FileService fileService;
    private final StoreOperationService operationService;

    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${name.of.data.file}")
    private String dataFileName;

    @Override
    public void add(SocksBatch socksBatch) {
        try {
            checkForValidate(socksBatch);
        } catch (ValidationException e) {
            e.printStackTrace();
            throw new RuntimeException("Переданые параметры некорректны.");
        }
        operationService.accept(socksBatch);
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
        operationService.issuance(socksBatch);
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

    @Override
    public File exportFile() throws IOException {
        return fileService.saveToFile(socksRepository.getList(), Path.of(dataFilePath)).toFile();
    }

    @Override
    public void importFile(MultipartFile file) throws IOException {
        List<SocksBatch> socksBatchList = fileService.uploadFromFile(file, dataFilePath, new TypeReference<List<SocksBatch>>() {
        });
        socksRepository.replace(socksBatchList);
    }

    private void checkForValidate(SocksBatch socksBatch) throws ValidationException {
        if (!validationService.validate(socksBatch)) {
            throw new ValidationException();
        }
    }
}