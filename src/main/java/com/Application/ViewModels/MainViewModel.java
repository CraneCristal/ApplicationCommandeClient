package com.Application.ViewModels;

import com.Application.Model.Customer;
import com.Application.Model.Model;

public class MainViewModel{
    public String[] customersName;
    public String[][] ordersCurrentCustomer;


    public MainViewModel(String[] customersName, String[][] ordersCurrentCustomer) {
        this.customersName = customersName;
        this.ordersCurrentCustomer = ordersCurrentCustomer;
    }
}
