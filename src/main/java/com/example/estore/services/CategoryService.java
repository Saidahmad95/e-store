package com.example.estore.services;


import com.example.estore.entities.Category;
import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.CategoryReq;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ApiResponse<Category> addCategory(CategoryReq request);

    ApiResponse<Category> editCategory(String id, CategoryReq request);

    ApiResponse<Category> deleteCategory(String id);
}
