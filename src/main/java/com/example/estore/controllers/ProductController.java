package com.example.estore.controllers;

import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.ProductCreationReq;
import com.example.estore.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//ProductController:
//product/addProduct
//product/editProduct
//product/deleteProduct
//product/assignAddon
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping("/addProduct")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductCreationReq request){
return productService.addProduct(request);
    }

}
