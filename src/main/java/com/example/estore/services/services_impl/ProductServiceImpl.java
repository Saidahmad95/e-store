package com.example.estore.services.services_impl;

import com.example.estore.entities.Category;
import com.example.estore.entities.Product;
import com.example.estore.entities.Store;
import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.ProductCreationReq;
import com.example.estore.repos.CategoryRepo;
import com.example.estore.repos.ProductRepo;
import com.example.estore.repos.StoreRepo;
import com.example.estore.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.estore.enums.ApiResponseMessages.*;
import static com.example.estore.util.CustomValidation.*;
import static com.example.estore.util.Mapper.responseEntityMaker;
import static java.util.UUID.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final StoreRepo storeRepo;

    @Override
    public ResponseEntity<ApiResponse> addProduct(ProductCreationReq request) {
        Optional<Category> categoryById = checkCategoryById(request);
        Optional<Store> storeById = checkStoreById(request);

        if (categoryById.isPresent() && storeById.isPresent()) {
            Product builtProduct = buildProduct(request, categoryById, storeById);
            Product savedProduct = productRepo.save(builtProduct);

            return responseEntityMaker(CREATED, PRODUCT_ADDED.getMessage(), savedProduct);
        }
        return buildApiResponseInFail(categoryById, storeById);
    }


    private Optional<Store> checkStoreById(ProductCreationReq request) {
        return validateUUID(request.getStoreId())
                ? storeRepo.findById(fromString(request.getStoreId())) : Optional.empty();
    }

    private Optional<Category> checkCategoryById(ProductCreationReq request) {
        return validateUUID(request.getCategoryId())
                ? categoryRepo.findById(fromString(request.getCategoryId())) : Optional.empty();
    }


    private Product buildProduct(ProductCreationReq request,
                                 Optional<Category> categoryById,
                                 Optional<Store> storeById) {
        return Product.builder()
                .category(categoryById.get())
                .store(storeById.get())
                .name(request.getName())
                .stock(request.getStock())
                .price(request.getPrice())
                .tax(request.getTax())
                .build();
    }


    private ResponseEntity<ApiResponse> buildApiResponseInFail(Optional<Category> categoryById,
                                                               Optional<Store> storeById) {
        if (categoryById.isEmpty()) {
            return responseEntityMaker(NOT_FOUND, CATEGORY_NOT_FOUND.getMessage(), null);
        } else if (storeById.isEmpty()) {
            return responseEntityMaker(NOT_FOUND, STORE_NOT_FOUND.getMessage(), null);
        } else {
            return responseEntityMaker(
                    NOT_FOUND,
                    CATEGORY_NOT_FOUND.getMessage().concat("\n").concat(STORE_NOT_FOUND.getMessage()),
                    null);
        }
    }


}
