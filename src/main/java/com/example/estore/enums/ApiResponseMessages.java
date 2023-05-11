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
    PRODUCT_ADDED("Product is successfully added"),


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
