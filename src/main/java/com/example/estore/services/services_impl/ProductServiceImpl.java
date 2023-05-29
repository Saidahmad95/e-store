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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.estore.enums.ApiResponseMessages.*;
import static com.example.estore.util.CustomValidation.*;
import static com.example.estore.util.Mapper.responseEntityMaker;
import static com.example.estore.util.Mapper.updateProduct;
import static java.util.UUID.*;
import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final StoreServiceImpl storeServiceImpl;

    @Override
    public ResponseEntity<ApiResponse> addProduct(ProductReq request) {
        //TODO Should change after creation category implementations
//        Optional<Category> categoryById = checkCategoryById(request.getCategoryId());
        Optional<Category> categoryById = Optional.of(new Category());
        Optional<Store> storeById = storeServiceImpl.checkStoreById(request.getStoreId());

        if (categoryById.isPresent() && storeById.isPresent()) {
            Product builtProduct = buildProduct(request, categoryById, storeById);
            Product savedProduct = productRepo.save(builtProduct);

            return responseEntityMaker(CREATED, PRODUCT_ADDED.getMessage(), savedProduct);
        }
        return buildApiResponseInFail(categoryById, storeById);
    }

    @Override
    public ResponseEntity<ApiResponse> editProduct(ProductReq request, String id) {
        Optional<Product> productById = checkProductById(id);
        if (productById.isPresent()) {
            Product foundProduct = productById.get();
            //TODO Change after Category implementation
//            Optional<Category> categoryById = checkCategoryById(request.getCategoryId());
            Optional<Category> categoryById = Optional.of(new Category());
            Optional<Store> storeById = storeServiceImpl.checkStoreById(request.getStoreId());

            if (categoryById.isPresent() && storeById.isPresent()) {
                Product updatedProduct = updateProduct(
                        foundProduct,
                        request,
                        storeById.get(),
                        categoryById.get());

                Product savedProduct = productRepo.save(updatedProduct);
                return responseEntityMaker(OK, PRODUCT_EDITED.getMessage(), savedProduct);
            }
            return buildApiResponseInFail(categoryById, storeById);
        }
        return responseEntityMaker(NOT_FOUND, PRODUCT_NOT_FOUND.getMessage(), null);
    }

    @Override
    public ResponseEntity<ApiResponse> deleteProduct(String uuid) {
        Optional<Product> productById = checkProductById(uuid);
        if (productById.isPresent()) {
//            productRepo.deleteById(fromString(uuid));
            Product foundProduct = productById.get();
            foundProduct.setDeleted(true);
            productRepo.save(foundProduct);
            return responseEntityMaker(OK, PRODUCT_DELETED.getMessage(), null);
        }
        return responseEntityMaker(NOT_FOUND, PRODUCT_NOT_FOUND.getMessage(), null);
    }

    @Override
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productRepo.findAll()
                .stream()
                .filter(product -> !product.isDeleted())
                .toList();

        return responseEntityMaker(OK,
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
