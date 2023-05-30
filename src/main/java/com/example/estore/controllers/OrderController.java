package com.example.estore.controllers;

import com.example.estore.entities.Order;
import com.example.estore.entities.OrderData;
import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.OrderDataReq;
import com.example.estore.payload.OrderReq;
import com.example.estore.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;

    @PostMapping("/addOrder")
    public ResponseEntity<ApiResponse<Order>> addOrder(@RequestBody OrderReq request) {
        ApiResponse<Order> orderApiResponse = orderService.addOrder(request);
        return ResponseEntity
                .status(orderApiResponse.getHttpStatus())
                .body(orderApiResponse);
    }

    @PostMapping("/addOrderData")
    public ResponseEntity<ApiResponse<OrderData>> addOrderData(@RequestBody OrderDataReq request) {
        ApiResponse<OrderData> orderDataApiResponse = orderService.addOrderData(request);
        return ResponseEntity
                .status(orderDataApiResponse.getHttpStatus())
                .body(orderDataApiResponse);
    }

    @GetMapping("/view-all-orders")
    public ResponseEntity<ApiResponse<List<Order>>> getAllOrders() {
        ApiResponse<List<Order>> allOrders = orderService.getAllOrders();
        return ResponseEntity
                .status(allOrders.getHttpStatus())
                .body(allOrders);
    }

    @GetMapping("/view-all-order-data")
    public ResponseEntity<ApiResponse<List<OrderData>>> getAllOrderData() {
        ApiResponse<List<OrderData>> allOrderData = orderService.getAllOrderData();
        return ResponseEntity
                .status(allOrderData.getHttpStatus())
                .body(allOrderData);
    }


}
