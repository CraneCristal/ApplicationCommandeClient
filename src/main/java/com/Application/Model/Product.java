package com.Application.Model;

public class Product {
    public String prductId;
    public String productName;
    public String standardCost;
    public String quantityPerUnit;
    public String category;

    public Product(String prductId, String productName, String standardCost, String quantityPerUnit, String category) {
        this.prductId = prductId;
        this.productName = productName;
        this.standardCost = standardCost;
        this.quantityPerUnit = quantityPerUnit;
        this.category = category;
    }
}
