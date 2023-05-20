package com.example.estore.controllers;

import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.OrderDataReq;
import com.example.estore.payload.OrderReq;
import com.example.estore.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;

    @PostMapping("/addOrder")
    public ResponseEntity<ApiResponse> addOrder(@RequestBody OrderReq request) {
        return orderService.addOrder(request);
    }

    @PostMapping("/addOrderData")
    public ResponseEntity<ApiResponse> addOrderData(@RequestBody OrderDataReq request) {
        return orderService.addOrderData(request);
    }

    @GetMapping("/view-all-orders")
    public ResponseEntity<ApiResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/view-all-order-data")
    public ResponseEntity<ApiResponse> getAllOrderData() {
        return orderService.getAllOrderData();
    }


}
