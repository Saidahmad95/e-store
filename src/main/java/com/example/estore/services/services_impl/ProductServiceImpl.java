package com.example.estore.services.services_impl;

import com.example.estore.entities.Category;
import com.example.estore.entities.Product;
import com.example.estore.entities.Store;
import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.ProductReq;
import com.example.estore.repos.CategoryRepo;
import com.example.estore.repos.ProductRepo;
import com.example.estore.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.estore.enums.ApiResponseMessages.*;
import static com.example.estore.util.CustomValidation.*;
import static com.example.estore.util.Mapper.*;
import static java.util.UUID.*;
import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final StoreServiceImpl storeServiceImpl;
    private final CategoryServiceImpl categoryService;

    @Override
    public ApiResponse<Product> addProduct(ProductReq request) {

        Optional<Category> categoryById = categoryService.checkCategoryById(request.getCategoryId());
        Optional<Store> storeById = storeServiceImpl.checkStoreById(request.getStoreId());

        if (categoryById.isPresent() && storeById.isPresent()) {
            Product builtProduct = buildProduct(request, categoryById, storeById);
            Product savedProduct = productRepo.save(builtProduct);

            return apiResponseMaker(CREATED, PRODUCT_ADDED.getMessage(), savedProduct);
        }
        return buildApiResponseInFail(categoryById, storeById);
    }

    @Override
    public ApiResponse<Product> editProduct(ProductReq request, String id) {
        Optional<Product> productById = checkProductById(id);
        if (productById.isPresent()) {
            Product foundProduct = productById.get();
            Optional<Category> categoryById = categoryService.checkCategoryById(request.getCategoryId());
            Optional<Store> storeById = storeServiceImpl.checkStoreById(request.getStoreId());

            if (categoryById.isPresent() && storeById.isPresent()) {
                Product updatedProduct = updateProduct(
                        foundProduct,
                        request,
                        storeById.get(),
                        categoryById.get());

                Product savedProduct = productRepo.save(updatedProduct);
                return apiResponseMaker(OK, PRODUCT_EDITED.getMessage(), savedProduct);
            }
            return buildApiResponseInFail(categoryById, storeById);
        }
        return apiResponseMaker(NOT_FOUND, PRODUCT_NOT_FOUND.getMessage(), null);
    }

    @Override
    public ApiResponse<Product> deleteProduct(String uuid) {
        Optional<Product> productById = checkProductById(uuid);
        if (productById.isPresent()) {
//            productRepo.deleteById(fromString(uuid));
            Product foundProduct = productById.get();
            foundProduct.setDeleted(true);
            productRepo.save(foundProduct);
            return apiResponseMaker(OK, PRODUCT_DELETED.getMessage());
        }
        return apiResponseMaker(NOT_FOUND, PRODUCT_NOT_FOUND.getMessage());
    }

    @Override
    public ApiResponse<List<Product>> getAllProducts() {
        List<Product> products = productRepo.findAll()
                .stream()
                .filter(product -> !product.isDeleted())
                .toList();

        return apiResponseMaker(OK,
                products.isEmpty() ? NO_PRODUCTS.getMessage() : TOTAL_PRODUCTS.getMessage() + products.size(),
                products);
    }

    //TODO Should move to CategoryService


    public Optional<Product> checkProductById(String productUuid) {
        return validateUUID(productUuid)
                ? productRepo.findById(fromString(productUuid)) : Optional.empty();
    }

    private Product buildProduct(ProductReq request,
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


    private ApiResponse buildApiResponseInFail(Optional<Category> categoryById,
                                               Optional<Store> storeById) {
        if (categoryById.isEmpty()) {
            return apiResponseMaker(NOT_FOUND, CATEGORY_NOT_FOUND.getMessage());
        } else if (storeById.isEmpty()) {
            return apiResponseMaker(NOT_FOUND, STORE_NOT_FOUND.getMessage());
        } else {
            return apiResponseMaker(
                    NOT_FOUND,
                    CATEGORY_NOT_FOUND.getMessage().concat("\n").concat(STORE_NOT_FOUND.getMessage()));
        }
    }


}
