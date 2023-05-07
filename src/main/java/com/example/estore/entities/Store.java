package com.example.estore.entities;


import com.example.estore.entities.common.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Store extends AbstractEntity {

    private String name;



}
