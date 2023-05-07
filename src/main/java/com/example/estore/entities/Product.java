package com.example.estore.entities;

import com.example.estore.entities.common.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Product extends AbstractEntity {
    private String name;

    private Integer stock;

    @ManyToOne(cascade = CascadeType.ALL)
    private Store store;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    private Double price;

    private Double tax;
}
