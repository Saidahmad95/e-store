package com.example.estore.repos;

import com.example.estore.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreRepo extends JpaRepository<Store, UUID> {

}
