package com.example.estore.util;

import com.example.estore.payload.ApiResponse;
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
}
