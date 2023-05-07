package com.example.estore.entities;


import com.example.estore.entities.common.AbstractEntity;
import com.example.estore.entities.enums.PayType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Order extends AbstractEntity {

    @ManyToOne
    private Store store;

    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private PayType payType;
}
