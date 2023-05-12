package com.example.estore.services;

import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.StoreReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface StoreService {
    ResponseEntity<ApiResponse> createStore(StoreReq request) ;
    ResponseEntity<ApiResponse> uploadAttachment(MultipartFile file, String id) throws IOException;
    ResponseEntity<ApiResponse> editStore(String id, StoreReq request);
    ResponseEntity<ApiResponse> getAllStores();
}
