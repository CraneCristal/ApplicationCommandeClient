package com.Application.Controllers;

public class ControllerResult {
    public String viewName;
    public Object viewModel;

    public ControllerResult(String viewName, Object viewModel) {
        this.viewName = viewName;
        this.viewModel = viewModel;
    }
}
