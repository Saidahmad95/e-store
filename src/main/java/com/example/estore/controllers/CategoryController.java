package com.example.estore.controllers;

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
    public ResponseEntity<ApiResponse> addCategory(@RequestBody CategoryReq request) {
        return categoryService.addCategory(request);
    }

    @PostMapping("/editCategory/{id}")
    public ResponseEntity<ApiResponse> editCategory(@PathVariable String id, @RequestBody CategoryReq request) {
        return categoryService.editCategory(id,request);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<ApiResponse>deleteCategory(@PathVariable String id){
        return categoryService.deleteCategory(id);
    }



}
