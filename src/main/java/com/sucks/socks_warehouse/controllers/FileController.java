package com.sucks.socks_warehouse.controllers;

import com.sucks.socks_warehouse.services.SocksWarehouseService;
import com.sucks.socks_warehouse.services.StoreOperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/socks/files")
@RequiredArgsConstructor
@Tag(name = "Работа с файлами",
        description = "Импорт и экспорт файлов с даннами по носкам на складе")
public class FileController {

    private final SocksWarehouseService socksWarehouseService;
    private final StoreOperationService storeOperationService;

    @Operation(
            summary = "Скачать файл с БД носков на складе",
            description = "Файл скачается в формате .json"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Файл успешно скачался."
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "Файл для скачивания не существует"
                    )
            }
    )
    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> downloadSocksJson() {
        try {
            File socksFile = socksWarehouseService.exportFile();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(socksFile));

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(socksFile.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + socksFile.getName())
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(
            summary = "Закачать файл на сервер",
            description = "Подходят только файлы формата .json с носочками"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "База носков успешно обновлена"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Файл не принят на сервер, либо ошибка сервера. Обратитесь к администратору"
            )
    })
    @PostMapping(value = "/import",
            consumes = "multipart/form-data")
    public ResponseEntity<String> uploadSocksJson(@RequestParam MultipartFile file) {
        try {
            socksWarehouseService.importFile(file);
            return ResponseEntity.ok("Файл успешно загружен");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
            summary = "Скачать файл с операциями произведенными на складе",
            description = "Файл скачается в формате .json"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Файл успешно скачался."
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "Файл для скачивания не существует"
                    )
            }
    )
    @GetMapping(value = "/operations/export")
    public ResponseEntity<InputStreamResource> downloadOperations() {
        try {
            File socksFile = storeOperationService.exportFile();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(socksFile));

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(socksFile.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + socksFile.getName())
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(
            summary = "Закачать файл на сервер",
            description = "Подходят только файлы формата .json с носками"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "База носков успешно обновлена"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Файл не принят на сервер, либо ошибка сервера. Обратитесь к администратору"
            )
    })
    @PostMapping(value = "/operations/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadSocksOperations(@RequestParam MultipartFile file) {
        try {
            storeOperationService.importFile(file);
            return ResponseEntity.ok("Файл успешно загружен");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}