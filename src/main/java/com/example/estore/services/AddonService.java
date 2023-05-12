package com.example.estore.services;

import com.example.estore.payload.AddonReq;
import com.example.estore.payload.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface AddonService {
    ResponseEntity<ApiResponse> addAddon(AddonReq request);

    ResponseEntity<ApiResponse> deleteAddon(String uuid);

    ResponseEntity<ApiResponse> getAllAddons();
}
