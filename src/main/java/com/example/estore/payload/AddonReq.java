package com.example.estore.payload;

import com.example.estore.entities.Product;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Builder
@Getter
@Setter
public class AddonReq {
    private String name;

    private Integer stock;

    private String productUuid;

    private Double price;
}
