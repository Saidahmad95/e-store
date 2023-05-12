package com.example.estore.util;

import com.example.estore.entities.Category;
import com.example.estore.entities.Product;
import com.example.estore.entities.Store;
import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.ProductReq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Mapper {
    public static ApiResponse apiResponseMaker(HttpStatus httpStatus,
                                        String message,
                                        Object data) {
        return ApiResponse
                .builder()
                .httpStatus(httpStatus)
                .message(message)
                .data(data)
                .build();
    }

    public static ResponseEntity<ApiResponse> responseEntityMaker(HttpStatus httpStatus,
                                                                  String message,
                                                                  Object data){
        ApiResponse response = apiResponseMaker(httpStatus, message, data);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    public static Product updateProduct(Product oldProduct,
                                        ProductReq productReq,
                                        Store store,
                                        Category category){
        oldProduct.setName(productReq.getName());
        oldProduct.setCategory(category);
        oldProduct.setStore(store);
        oldProduct.setStock(productReq.getStock());
        oldProduct.setPrice(productReq.getPrice());
        oldProduct.setTax(productReq.getTax());

        return oldProduct;
    }
}
