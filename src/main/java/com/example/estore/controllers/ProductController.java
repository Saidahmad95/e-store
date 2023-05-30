package com.example.estore.controllers;

import com.example.estore.entities.Product;
import com.example.estore.payload.ApiResponse;
import com.example.estore.payload.ProductReq;
import com.example.estore.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ApiResponse<Product>> addProduct(@RequestBody ProductReq request) {
        ApiResponse<Product> productApiResponse = productService.addProduct(request);
        return ResponseEntity
                .status(productApiResponse.getHttpStatus())
                .body(productApiResponse);
    }

    @PostMapping("/editProduct/{uuid}")
    public ResponseEntity<ApiResponse<Product>> editProduct(@RequestBody ProductReq request,
                                                            @PathVariable String uuid) {
        ApiResponse<Product> productApiResponse = productService.editProduct(request, uuid);
        return ResponseEntity
                .status(productApiResponse.getHttpStatus())
                .body(productApiResponse);
    }

    @DeleteMapping("/deleteProduct/{uuid}")
    public ResponseEntity<ApiResponse<Product>> deleteProduct(@PathVariable String uuid) {
        ApiResponse<Product> productApiResponse = productService.deleteProduct(uuid);
        return ResponseEntity
                .status(productApiResponse.getHttpStatus())
                .body(productApiResponse);
    }

    //TODO  create product/assignAddon api

    @GetMapping("/view-all")
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        ApiResponse<List<Product>> allProducts = productService.getAllProducts();
        return ResponseEntity
                .status(allProducts.getHttpStatus())
                .body(allProducts);
    }


}
