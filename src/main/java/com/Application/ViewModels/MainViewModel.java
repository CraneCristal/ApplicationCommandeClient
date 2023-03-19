package com.Application.ViewModels;

public class MainViewModel{
    public String[] customersId;
    public String[][] ordersCurrentCustomer;


    public MainViewModel(String[] customersId, String[][] ordersCurrentCustomer) {
        this.customersId = customersId;
        this.ordersCurrentCustomer = ordersCurrentCustomer;
    }
}
