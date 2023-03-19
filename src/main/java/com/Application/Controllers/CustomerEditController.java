package com.Application.Controllers;

import com.Application.Model.Customer;
import com.Application.Model.Model;
import com.Application.ViewModels.CustomerEditViewModel;

public class CustomerEditController extends Controller{
    public CustomerEditController(Model model) {
        super(model);
    }

    public ControllerResult run(Object... args) {
        Customer customer = this.model.getCustomer((String) args[0]);
        CustomerEditViewModel viewModel = new CustomerEditViewModel(customer.first_name, customer.last_name);
        return new ControllerResult("CustomerEditView", viewModel);
    }
}
