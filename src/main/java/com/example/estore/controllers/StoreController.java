package com.example.estore.controllers;


import com.example.estore.enums.ValidationMessages;
import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.StoreCreationReq;
import com.example.estore.services.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

import static com.example.estore.enums.ValidationMessages.UUID_VALIDATION_MESSAGE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/createStore")
    public ResponseEntity<ApiResponse> createStore(@RequestBody StoreCreationReq request) {
        return storeService.createStore(request);
    }

    @PostMapping("uploadAttachment")
    public ResponseEntity<ApiResponse> uploadAttachment(@RequestParam("file") MultipartFile multipartFile,
                                                         @RequestParam("id") String id) throws IOException {
        return storeService.uploadAttachment(multipartFile, id);
    }


}
