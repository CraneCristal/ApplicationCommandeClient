package com.Application.ViewModels;

public class CustomerEditViewModel {
    public String firstName;
    public String lastName;
    public String hash;

    public CustomerEditViewModel(String firstName, String lastName, String hash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hash = hash;
    }
}
