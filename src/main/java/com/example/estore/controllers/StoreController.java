package com.example.estore.controllers;


import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.StoreReq;
import com.example.estore.services.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/createStore")
    public ResponseEntity<ApiResponse> createStore(@RequestBody StoreReq request) {
        return storeService.createStore(request);
    }

    @PostMapping("uploadAttachment")
    public ResponseEntity<ApiResponse> uploadAttachment(@RequestParam("file") MultipartFile multipartFile,
                                                         @PathVariable String id) throws IOException {
        return storeService.uploadAttachment(multipartFile, id);
    }

    @PutMapping("editStore/{id}")
    public ResponseEntity<ApiResponse> editStore(@PathVariable String id,
                                                 @RequestBody StoreReq request ){
        return storeService.editStore(id,request);
    }

    @GetMapping("view-all")
    public ResponseEntity<ApiResponse> getAllStores(){
        return storeService.getAllStores();
    }


}
