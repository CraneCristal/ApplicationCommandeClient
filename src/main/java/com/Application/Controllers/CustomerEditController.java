package com.Application.Controllers;

import com.Application.Model.Model;

public class CustomerEditController extends Controller{
    public CustomerEditController(Model model) {
        super(model);
    }

    public ControllerResult run(Object... args) {
        return new ControllerResult(null, null);
    }
}
