package com.Application.ViewModels;

public class ProductViewModel {
    public String productId;
    public String productName;
    public String productCategory;
    public String standardCost;
    public String productQuantityPerUnit;
    public String customerId;

    public ProductViewModel(String productId, String productName, String productCategory, String standardCost, String productQuantityPerUnit, String customerId) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.standardCost = standardCost;
        this.productQuantityPerUnit = productQuantityPerUnit;
        this.customerId = customerId;
    }
}
