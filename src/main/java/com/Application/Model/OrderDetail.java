package com.Application.Model;

public class OrderDetail {
    public String productId;
    public String quantity;
    public String unitPrice;
    public String discount;

    public OrderDetail(String productId, String quantity, String unitPrice, String discount) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }
}
