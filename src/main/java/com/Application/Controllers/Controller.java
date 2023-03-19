package com.Application.Controllers;

import com.Application.Model.Model;

public abstract class Controller {
    protected Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public abstract ControllerResult run(Object ... args);
}
