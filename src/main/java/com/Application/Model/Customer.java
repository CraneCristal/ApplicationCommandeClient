package com.Application.Model;

public class Customer {
    public String id;
    public String first_name;
    public String last_name;

    public String hash;

    public Customer(String id, String first_name, String last_name, String hash) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.hash=hash;
    }
}
