package com.cart.pojo;
import lombok.Data;

@Data
public class Product {

    private long productId;
    private long providerId;
    private String productName;
    private String productCategory;
    private double productPrice;
    private String productDescription;
}

