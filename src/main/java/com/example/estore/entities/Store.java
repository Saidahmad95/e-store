package com.example.estore.entities;


import com.example.estore.entities.common.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Store extends AbstractEntity {

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment attachment;


    public Store(String name) {
        this.name = name;
    }
}
