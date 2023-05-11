package com.example.estore.services;

import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.ProductCreationReq;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    ResponseEntity<ApiResponse> addProduct(ProductCreationReq request);
}
