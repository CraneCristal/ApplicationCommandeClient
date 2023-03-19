package com.Application.Model;

public class Order {
    public String id;
    public String date;
    public String customer_id;

    public String fullPrice;

    public Order(String id, String date, String customer_id, String fullPrice) {
        this.id = id;
        this.date = date;
        this.customer_id = customer_id;
        this.fullPrice = fullPrice;
    }
}
