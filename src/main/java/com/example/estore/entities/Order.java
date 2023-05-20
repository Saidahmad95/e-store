package com.example.estore.entities;


import com.example.estore.entities.common.AbstractEntity;
import com.example.estore.enums.PayType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private PayType payType;
}
