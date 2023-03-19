package com.Application.Views;

import org.eclipse.swt.widgets.Shell;

public abstract class View {
    public Object viewModel;
    public ControllerCall controllerCall;
    protected Shell shell;

    public View(Shell shell, Object viewModel) {
        this.shell = shell;
        this.viewModel = viewModel;
    }

    public abstract void show();

    public abstract void update();
}
