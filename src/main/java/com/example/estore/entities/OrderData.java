package com.example.estore.entities;

import com.example.estore.entities.common.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class OrderData extends AbstractEntity {

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    private Integer productCount;

    @ManyToOne
    private Addon addon;

    private Integer addonCount;

    private BigDecimal totalPrice;
}
