package com.example.estore.services.services_impl;

import com.example.estore.entities.*;
import com.example.estore.enums.PayType;
import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.OrderDataReq;
import com.example.estore.payload.OrderReq;
import com.example.estore.repos.OrderDataRepo;
import com.example.estore.repos.OrderRepo;
import com.example.estore.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.example.estore.enums.ApiResponseMessages.*;
import static com.example.estore.util.CustomValidation.validateUUID;
import static com.example.estore.util.Mapper.apiResponseMaker;
import static com.example.estore.util.Mapper.responseEntityMaker;
import static java.util.UUID.fromString;
import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final StoreServiceImpl storeServiceImpl;
    private final OrderRepo orderRepo;
    private final ProductServiceImpl productServiceImpl;
    private final AddonServiceImpl addonServiceImpl;
    private final OrderDataRepo orderDataRepo;

    @Override
    public ResponseEntity<ApiResponse> addOrder(OrderReq request) {
        List<ApiResponse> failureResponses = new ArrayList<>();

        Optional<Store> storeById = storeServiceImpl.checkStoreById(request.getStoreId());
        if (storeById.isEmpty())
            failureResponses.add(apiResponseMaker(NOT_FOUND, STORE_NOT_FOUND.getMessage(), null));

        PayType payType = findPayType(request.getPayType());
        if (payType == null)
            failureResponses.add(apiResponseMaker(NOT_FOUND, PAY_TYPE_NOT_FOUND.getMessage(), null));

        if (!failureResponses.isEmpty()) {
//            return ResponseEntity.status(BAD_REQUEST).body(Optional.of(failureResponses));
            return responseEntityMaker(BAD_REQUEST,WENT_WRONG.getMessage(),failureResponses);
        }
        Order savedOrder = orderRepo.save(buildOrder(request, storeById, payType));
        return responseEntityMaker(CREATED, ORDER_ADDED.getMessage(), savedOrder);

    }

    @Override
    public ResponseEntity<ApiResponse> addOrderData(OrderDataReq request) {


        Optional<Order> orderOptional = findOrder(request.getOrderId());
        Optional<Product> productById = productServiceImpl.checkProductById(request.getProductId());
        Optional<Addon> addonById = addonServiceImpl.checkAddonById(request.getAddonId());

        List<ApiResponse> responses = collectFailedResponses(orderOptional, productById, addonById);
        if (!responses.isEmpty()) {
//         ResponseEntity.status(BAD_REQUEST).body(Optional.of(responses));
            return responseEntityMaker(BAD_REQUEST, WENT_WRONG.getMessage(), responses);
        }
        OrderData savedOrderData = orderDataRepo.save(
                buildOrderData(request, orderOptional, productById, addonById));

        return responseEntityMaker(CREATED, ORDER_DATA_ADDED.getMessage(), savedOrderData);
    }

    private static List<ApiResponse> collectFailedResponses(Optional<Order> orderOptional,
                                                            Optional<Product> productById,
                                                            Optional<Addon> addonById) {
        List<ApiResponse> failureResponses = new ArrayList<>();
        if (orderOptional.isEmpty()) {
            failureResponses.add(apiResponseMaker(NOT_FOUND, ORDER_NOT_FOUND.getMessage(), null));
        }
        if (productById.isEmpty()) {
            failureResponses.add(apiResponseMaker(NOT_FOUND, PRODUCT_NOT_FOUND.getMessage(), null));
        }
        if (addonById.isEmpty())
            failureResponses.add(apiResponseMaker(NOT_FOUND, ADDON_NOT_FOUND.getMessage(), null));

        return failureResponses;
    }

    @Override
    public ResponseEntity<ApiResponse> getAllOrders() {
        List<Order> orderList = orderRepo.findAll()
                .stream()
                .filter(order -> !order.isDeleted())
                .toList();

        return responseEntityMaker(OK,
                orderList.isEmpty() ? NO_ORDERS.getMessage() : TOTAL_ORDERS.getMessage() + orderList.size(),
                orderList);
    }

    @Override
    public ResponseEntity<ApiResponse> getAllOrderData() {
        List<OrderData> orderDataList = orderDataRepo.findAll()
                .stream()
                .filter(orderData -> !orderData.isDeleted())
                .toList();

        return responseEntityMaker(OK,
                orderDataList.isEmpty() ? NO_ORDER_DATA.getMessage() : TOTAL_ORDER_DATA.getMessage() + orderDataList.size(),
                orderDataList);
    }


    public Optional<Order> findOrder(String orderUuid) {
        return validateUUID(orderUuid) ? orderRepo.findById(fromString(orderUuid)) : Optional.empty();
    }

    private static OrderData buildOrderData(OrderDataReq request, Optional<Order> orderOptional, Optional<Product> productById, Optional<Addon> addonById) {
        return OrderData.builder()
                .order(orderOptional.get())
                .product(productById.get())
                .addon(addonById.get())
                .productCount(request.getProductCount())
                .addonCount(request.getAddonCount())
                .totalPrice(request.getTotalPrice())
                .build();
    }

    private static Order buildOrder(OrderReq request, Optional<Store> storeById, PayType payType) {
        return Order.builder()
                .store(storeById.get())
                .payType(payType)
                .totalPrice(request.getTotalPrice())
                .build();
    }

    private PayType findPayType(String paytype) {
        return Arrays.stream(PayType.values())
                .filter(payType1 -> payType1.name().equals(paytype))
                .findAny().orElse(null);
    }
}

