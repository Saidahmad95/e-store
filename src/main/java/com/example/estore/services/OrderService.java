package com.example.estore.services;

import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.OrderDataReq;
import com.example.estore.payload.OrderReq;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<ApiResponse> addOrder(OrderReq request);

    ResponseEntity<ApiResponse> addOrderData(OrderDataReq request);
}
