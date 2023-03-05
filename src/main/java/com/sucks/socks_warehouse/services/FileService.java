package com.sucks.socks_warehouse.services;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileService {
    <T> Path saveToFile(T content, Path path) throws IOException;

    <T> List<T> uploadFromFile(MultipartFile file, Path path, TypeReference<List<T>> typeReference) throws IOException;
}