package com.Application.ViewModels;

public class OrderDetailsViewModel {
    public String[][] details;
    public String customerId;

    public OrderDetailsViewModel(String[][] details, String customerId) {
        this.details = details;
        this.customerId = customerId;
    }
}
