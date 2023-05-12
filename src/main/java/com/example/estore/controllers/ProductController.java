package com.example.estore.controllers;

import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.ProductReq;
import com.example.estore.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductReq request) {
        return productService.addProduct(request);
    }

    @PostMapping("/editProduct/{uuid}")
    public ResponseEntity<ApiResponse> editProduct(@RequestBody ProductReq request,
                                                   @PathVariable String uuid){
        return productService.editProduct(request, uuid);
    }

    @DeleteMapping("/deleteProduct/{uuid}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable String uuid){
        return productService.deleteProduct(uuid);
    }

    //TODO  create product/assignAddon api

    @GetMapping("/view-all")
    public ResponseEntity<ApiResponse> getAllProducts(){
        return productService.getAllProducts();
    }


}
