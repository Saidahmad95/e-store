package com.example.estore.entities;

import com.example.estore.entities.common.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderData extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private Integer productCount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Addon addon;

    private Integer addonCount;

    private BigDecimal totalPrice;
}
