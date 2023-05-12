package com.example.estore.repos;

import com.example.estore.entities.Addon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddonRepo  extends JpaRepository<Addon, UUID> {
}
