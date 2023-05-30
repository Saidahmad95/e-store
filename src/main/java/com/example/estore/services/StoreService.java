package com.example.estore.services;

import com.example.estore.entities.Store;
import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.StoreReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


public interface StoreService {
    ApiResponse<Store> createStore(StoreReq request);

    ApiResponse<Path> uploadAttachment(MultipartFile file, String id) throws IOException;

    ApiResponse<Store> editStore(String id, StoreReq request);

    ApiResponse<List<Store>> getAllStores();
}
