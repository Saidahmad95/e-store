package com.example.estore.entities;

import com.example.estore.entities.common.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;



@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AbstractEntity {
    private String name;

    private Integer stock;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Store store;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Category category;

    private Double price;

    private Double tax;
}
