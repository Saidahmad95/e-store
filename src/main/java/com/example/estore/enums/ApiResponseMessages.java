package com.example.estore.enums;

public enum ApiResponseMessages {
    //ATACHMENT
    ATTACHMENT_UPLOAD("Attachment is successfully uploaded !"),


    // STORE
    STORE_CREATED("Store is successfully created !"),
    STORE_NOT_FOUND("Store not found !"),
    NO_STORES("No stores available !"),
    TOTAL_STORES("Total store(s): "),
    STORE_UPDATED("Store successfully updated !"),


    // PRODUCT
    PRODUCT_ADDED("Product is successfully added !"),
    PRODUCT_EDITED("Product is successfully edited !"),
    PRODUCT_NOT_FOUND("Product not found !"),
    PRODUCT_DELETED("Product successfully deleted !"),
    NO_PRODUCTS("No products available !"),
    TOTAL_PRODUCTS("Total product(s): "),


    // CATEGORY
    CATEGORY_NOT_FOUND("Category not found !");

    private String message;

    ApiResponseMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
