package com.example.estore.services;


import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.CategoryReq;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<ApiResponse> addCategory(CategoryReq request);

    ResponseEntity<ApiResponse> editCategory(String id, CategoryReq request);

    ResponseEntity<ApiResponse> deleteCategory(String id);
}
