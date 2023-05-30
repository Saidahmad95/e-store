package com.example.estore.services;

import com.example.estore.entities.Addon;
import com.example.estore.payload.AddonReq;
import com.example.estore.payload.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AddonService {
    ApiResponse<Addon> addAddon(AddonReq request);

   ApiResponse<Addon> deleteAddon(String uuid);

   ApiResponse<List<Addon>> getAllAddons();
}
