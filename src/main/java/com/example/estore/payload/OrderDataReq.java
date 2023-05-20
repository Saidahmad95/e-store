package com.example.estore.payload;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderDataReq {

    private String orderId;

    private String productId;

    private Integer productCount;

    private String addonId;

    private Integer addonCount;

    private BigDecimal totalPrice;
}
