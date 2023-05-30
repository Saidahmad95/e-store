package com.example.estore.services.services_impl;

import com.example.estore.entities.Category;
import com.example.estore.entities.Product;
import com.example.estore.enums.ApiResponseMessages;
import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.CategoryReq;
import com.example.estore.repos.CategoryRepo;
import com.example.estore.services.CategoryService;
import com.example.estore.util.CustomValidation;
import com.example.estore.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.example.estore.enums.ApiResponseMessages.*;
import static com.example.estore.util.CustomValidation.validateUUID;
import static com.example.estore.util.Mapper.responseEntityMaker;
import static java.util.UUID.fromString;
import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public ResponseEntity<ApiResponse> addCategory(CategoryReq request) {
        Category builtCategory = Category.builder().name(request.getCategoryName()).build();
        Category savedCategory = categoryRepo.save(builtCategory);
        return responseEntityMaker(CREATED, CATEGORY_ADDED.getMessage(), savedCategory);
    }

    @Override
    public ResponseEntity<ApiResponse> editCategory(String id, CategoryReq request) {
        Optional<Category> categoryById = checkCategoryById(id);
        if (categoryById.isPresent()) {
            Category foundCategory = categoryById.get();
            foundCategory.setName(request.getCategoryName());
            Category savedCategory = categoryRepo.save(foundCategory);
            return responseEntityMaker(OK, CATEGORY_EDITED.getMessage(), savedCategory);
        }
        return responseEntityMaker(NOT_FOUND, CATEGORY_NOT_FOUND.getMessage(), null);
    }

    @Override
    public ResponseEntity<ApiResponse> deleteCategory(String id) {
        Optional<Category> categoryById = checkCategoryById(id);
        if (categoryById.isPresent()) {
            Category foundCategory = categoryById.get();
            foundCategory.setDeleted(true);
            categoryRepo.save(foundCategory);
            return responseEntityMaker(OK, CATEGORY_DELETED.getMessage(), null);
        }
        return responseEntityMaker(NOT_FOUND, CATEGORY_NOT_FOUND.getMessage(), null);
    }

    private Optional<Category> checkCategoryById(String uuid) {
        return validateUUID(uuid)
                ? categoryRepo.findById(fromString(uuid)) : Optional.empty();
    }
}
