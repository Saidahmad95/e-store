package com.example.estore.util;

import com.example.estore.entities.Category;
import com.example.estore.entities.Product;
import com.example.estore.entities.Store;
import com.example.estore.payload.ApiFailedResponse;
import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.ProductReq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedList;
import java.util.List;

public class Mapper {
    public static <T> ApiResponse<T> apiResponseMaker(HttpStatus httpStatus,
                                                      String message,
                                                      T data) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setData(data);
        apiResponse.setMessage(message);
        apiResponse.setHttpStatus(httpStatus);
        return apiResponse;

    }

    public static  ApiResponse apiResponseMaker(HttpStatus httpStatus,
                                                      String message,
                                                      List<ApiResponse> responses) {
        return ApiResponse.builder()
                .data(responses)
                .httpStatus(httpStatus)
                .message(message)
                .build();
    }

    public static ApiResponse apiResponseMaker(HttpStatus httpStatus,
                                               String message) {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setMessage(message);

        apiResponse.setHttpStatus(httpStatus);
        return apiResponse;

    }

//    public static ApiFailedResponse apiResponseMaker(HttpStatus httpStatus,
//                                                     String message) {
//        return ApiFailedResponse.builder()
//                .message(message)
//                .httpStatus(httpStatus)
//                .build();
//
//    }

    public static <T> ResponseEntity<ApiResponse<T>> responseEntityMaker(HttpStatus httpStatus,
                                                                         String message,
                                                                         T data) {
        ApiResponse<T> response = apiResponseMaker(httpStatus, message, data);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

//    public static  ResponseEntity<List<ApiFailedResponse>> responseEntityMaker(HttpStatus httpStatus,
//                                                                         List<ApiFailedResponse> failureResponses ){
//        return ResponseEntity.status(httpStatus).body(failureResponses);
//    }


    public static Product updateProduct(Product oldProduct,
                                        ProductReq productReq,
                                        Store store,
                                        Category category) {
        oldProduct.setName(productReq.getName());
        oldProduct.setCategory(category);
        oldProduct.setStore(store);
        oldProduct.setStock(productReq.getStock());
        oldProduct.setPrice(productReq.getPrice());
        oldProduct.setTax(productReq.getTax());

        return oldProduct;
    }
}
