package com.example.estore.util;

import com.example.estore.payload.ApiResponse;
import org.springframework.http.HttpStatus;

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
}
