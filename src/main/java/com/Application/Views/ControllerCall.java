package com.Application.Views;

public class ControllerCall {
    // Classe de la vue utilisant le ControllerCall
    public Class<?> controllerClass;
    // Les arguments de l'appel
    public Object[] args;

    public ControllerCall(Class<?> controllerClass, Object... args) {
        this.controllerClass = controllerClass;
        this.args = args;
    }

    public ControllerCall() {
        this.args = null;
        this.controllerClass = null;
    }
}
