package com.example.estore.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderReq {
    private String  storeId;
    private Double totalPrice;
    private String payType;
}
