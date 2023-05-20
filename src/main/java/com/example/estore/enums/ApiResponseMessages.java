package com.example.estore.enums;

public enum ApiResponseMessages {
    //ATACHMENT
    ATTACHMENT_UPLOAD("Attachment is successfully uploaded !"),


    // STORE
    STORE_CREATED("Store successfully created !"),
    STORE_NOT_FOUND("Store not found !"),
    NO_STORES("No stores available !"),
    TOTAL_STORES("Total store(s): "),
    STORE_UPDATED("Store successfully updated !"),


    // PRODUCT
    PRODUCT_ADDED("Product successfully added !"),
    PRODUCT_EDITED("Product successfully edited !"),
    PRODUCT_NOT_FOUND("Product not found !"),
    PRODUCT_DELETED("Product successfully deleted !"),
    NO_PRODUCTS("No products available !"),
    TOTAL_PRODUCTS("Total product(s): "),


    // CATEGORY
    CATEGORY_NOT_FOUND("Category not found !"),



    // ADDON
    ADDON_ADDED("Addon successfully added !"),
    ADDON_NOT_FOUND("Addon not found !"),
    ADDON_DELETED("Addon successfully deleted!"),
    TOTAL_ADDONS("Total addon(s): "),
    NO_ADDONS("No addons available !"),



    // ORDER
    ORDER_ADDED("Order successfully added !"),
    ORDER_NOT_FOUND("Order not found !"),
    NO_ORDERS("No orders available !"),
    TOTAL_ORDERS("Total order(s): "),

    // ORDER_DATA
    ORDER_DATA_ADDED("Order-data successfully added !"),
    NO_ORDER_DATA("No order-data available !"),
    TOTAL_ORDER_DATA("Total order-data(s): "),


    // PayType
    PAY_TYPE_NOT_FOUND("Pay-types not found !");



    private String message;

    ApiResponseMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
