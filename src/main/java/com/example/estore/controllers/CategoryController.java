package com.example.estore.controllers;

import com.example.estore.entities.Category;
import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.CategoryReq;
import com.example.estore.payload.OrderReq;
import com.example.estore.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/addCategory")
    public ResponseEntity<ApiResponse<Category>> addCategory(@RequestBody CategoryReq request) {
        ApiResponse<Category> categoryApiResponse = categoryService.addCategory(request);
        return ResponseEntity
                .status(categoryApiResponse.getHttpStatus())
                .body(categoryApiResponse);
    }

    @PostMapping("/editCategory/{id}")
    public ResponseEntity<ApiResponse<Category>> editCategory(@PathVariable String id,
                                                              @RequestBody CategoryReq request) {
        ApiResponse<Category> categoryApiResponse = categoryService.editCategory(id, request);
        return ResponseEntity
                .status(categoryApiResponse.getHttpStatus())
                .body(categoryApiResponse);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<ApiResponse<Category>> deleteCategory(@PathVariable String id) {
        ApiResponse<Category> categoryApiResponse = categoryService.deleteCategory(id);
        return ResponseEntity
                .status(categoryApiResponse.getHttpStatus())
                .body(categoryApiResponse);
    }


}
