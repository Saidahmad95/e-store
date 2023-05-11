package com.example.estore.entities;

import com.example.estore.entities.common.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Getter
@Setter
public class Addon extends AbstractEntity {

    private String name;

    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private Double price;
}
