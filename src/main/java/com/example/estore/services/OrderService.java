package com.example.estore.services;

import com.example.estore.entities.Order;
import com.example.estore.entities.OrderData;
import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.OrderDataReq;
import com.example.estore.payload.OrderReq;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    ApiResponse<Order> addOrder(OrderReq request);

    ApiResponse<OrderData> addOrderData(OrderDataReq request);

    ApiResponse<List<Order>> getAllOrders();

    ApiResponse<List<OrderData>> getAllOrderData();
}
