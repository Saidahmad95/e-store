package com.example.estore.services;

import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.ProductReq;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    ResponseEntity<ApiResponse> addProduct(ProductReq request);

    ResponseEntity<ApiResponse> editProduct(ProductReq request, String id);

    ResponseEntity<ApiResponse> deleteProduct(String uuid);

    ResponseEntity<ApiResponse> getAllProducts();
}
