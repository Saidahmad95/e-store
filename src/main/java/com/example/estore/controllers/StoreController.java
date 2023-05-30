package com.example.estore.controllers;


import com.example.estore.entities.Store;
import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.StoreReq;
import com.example.estore.services.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/createStore")
    public ResponseEntity<ApiResponse<Store>> createStore(@RequestBody StoreReq request) {
        ApiResponse<Store> storeApiResponse = storeService.createStore(request);
        return ResponseEntity
                .status(storeApiResponse.getHttpStatus())
                .body(storeApiResponse);
    }

    @PostMapping("uploadAttachment")
    public ResponseEntity<ApiResponse<Path>> uploadAttachment(@RequestParam("file") MultipartFile multipartFile,
                                                              @PathVariable String id) throws IOException {
        ApiResponse<Path> pathApiResponse = storeService.uploadAttachment(multipartFile, id);
        return ResponseEntity
                .status(pathApiResponse.getHttpStatus())
                .body(pathApiResponse);
    }

    @PutMapping("editStore/{id}")
    public ResponseEntity<ApiResponse<Store>> editStore(@PathVariable String id,
                                                        @RequestBody StoreReq request) {
        ApiResponse<Store> storeApiResponse = storeService.editStore(id, request);
        return ResponseEntity
                .status(storeApiResponse.getHttpStatus())
                .body(storeApiResponse);
    }

    @GetMapping("view-all")
    public ResponseEntity<ApiResponse<List<Store>>> getAllStores() {
        ApiResponse<List<Store>> allStores = storeService.getAllStores();
        return ResponseEntity
                .status(allStores.getHttpStatus())
                .body(allStores);

    }


}
