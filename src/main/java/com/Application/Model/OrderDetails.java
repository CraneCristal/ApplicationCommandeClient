package com.Application.Model;

public class OrderDetails {
    public String productId;
    public String quantity;
    public String unitPrice;
    public String discount;

    public OrderDetails(String productId, String quantity, String unitPrice, String discount) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }
}
