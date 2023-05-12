package com.example.estore.payload;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductReq {
    private String name;
    private Integer stock;
    private String storeId;
    private String categoryId;
    private Double price;
    private Double tax;

}
