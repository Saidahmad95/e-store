package com.example.estore.repos;


import com.example.estore.entities.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderDataRepo extends JpaRepository<OrderData, UUID> {
}
