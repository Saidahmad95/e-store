package com.example.estore.controllers;

import com.example.estore.entities.Addon;
import com.example.estore.payload.AddonReq;
import com.example.estore.payload.ApiResponse;
import com.example.estore.services.AddonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addon")
@RequiredArgsConstructor
public class AddonController {

    private final AddonService addonService;

    @PostMapping("/addAddon")
    public ResponseEntity<ApiResponse<Addon>> addAddon(@RequestBody AddonReq request) {
        ApiResponse<Addon> addonApiResponse = addonService.addAddon(request);
        return ResponseEntity.status(addonApiResponse.getHttpStatus()).body(addonApiResponse);
    }

    @DeleteMapping("/deleteAddon/{uuid}")
    public ResponseEntity<ApiResponse<Addon>> deleteAddon(@PathVariable String uuid) {
        ApiResponse<Addon> addonApiResponse = addonService.deleteAddon(uuid);
        return ResponseEntity.status(addonApiResponse.getHttpStatus()).body(addonApiResponse);
    }

    @GetMapping("/view-all")
    public ResponseEntity<ApiResponse<List<Addon>>> getAllAddons() {
        ApiResponse<List<Addon>> allAddons = addonService.getAllAddons();
        return ResponseEntity.status(allAddons.getHttpStatus()).body(allAddons);

    }
}
