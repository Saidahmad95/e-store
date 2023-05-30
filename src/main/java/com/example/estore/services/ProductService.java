package com.example.estore.services;

import com.example.estore.entities.Product;
import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.ProductReq;
import jakarta.validation.constraints.Null;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

   ApiResponse<Product> addProduct(ProductReq request);

    ApiResponse<Product> editProduct(ProductReq request, String id);

    ApiResponse<Product> deleteProduct(String uuid);

    ApiResponse<List<Product>> getAllProducts();
}
