package com.Application.Model;

public class Product {
    public String productId;
    public String productName;
    public String standardCost;
    public String quantityPerUnit;
    public String category;

    public Product(String productId, String productName, String standardCost, String quantityPerUnit, String category) {
        this.productId = productId;
        this.productName = productName;
        this.standardCost = standardCost;
        this.quantityPerUnit = quantityPerUnit;
        this.category = category;
    }
}
