package com.example.estore.controllers;

import com.example.estore.payload.AddonReq;
import com.example.estore.payload.ApiResponse;
import com.example.estore.services.AddonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addon")
@RequiredArgsConstructor
public class AddonController {

    private final AddonService addonService;

    @PostMapping("/addAddon")
    public ResponseEntity<ApiResponse> addAddon(@RequestBody AddonReq request) {
        return addonService.addAddon(request);
    }

    @DeleteMapping("/deleteAddon/{uuid}")
    public ResponseEntity<ApiResponse> deleteAddon(@PathVariable String uuid){
                return addonService.deleteAddon(uuid);
    }

    @GetMapping("/view-all")
    public ResponseEntity<ApiResponse> getAllAddons(){
        return addonService.getAllAddons();
    }
}
