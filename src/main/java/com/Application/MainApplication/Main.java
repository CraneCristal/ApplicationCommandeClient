package com.Application.MainApplication;

import com.Application.Controllers.Controller;
import com.Application.Controllers.MainController;
import com.Application.Model.Model;
import com.Application.Views.*;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;

public class Main {
    private Model model;
    private Shell shell;
    private View actualView;

    public static void main(String[] args) {new Main().run();}

    public void run() {
        // Mise en place de la fenetre
        Display display = new Display();
        this.shell = new Shell(display);
        this.shell.setSize(800, 600);
        this.shell.open();


        this.model = new Model();

        while (!this.shell.isDisposed()) {
            // Lorsqu'on ouvre l'application on appel le MainController pour avoir la MainView
            if(this.actualView == null) {
                this.actualView = runController(new ControllerCall(MainController.class, 0));
                this.actualView.show();
            }
            if(!display.readAndDispatch())
                display.sleep();
            else if(this.actualView.controllerCall != null){
                // Si la nouvelle vue est du meme type que l'ancienne, on midifie juste le viewModel associé
                View newView = runController(this.actualView.controllerCall);
                if(newView.getClass() == this.actualView.getClass()) {
                    this.actualView.viewModel = newView.viewModel;
                    this.actualView.update();
                }
                else {
                    this.actualView = newView;
                    this.actualView.show();
                }
            }
        }
    }


    public View runController(ControllerCall controllerCall) {
        Controller controller = createController(controllerCall.controllerClass);
        Object viewModel = controller.run(controllerCall.args).viewModel;
        String viewName = "com.Application.Views." + controllerCall.controllerClass.getSimpleName().replaceAll("Controller", "View");
        return getView(viewName, viewModel);
    }

    public Controller createController(Class<?> controllerClass) {
        try {
            //  Récupération du constructeur de la classe du contrôleur qui accepte un argument de type Model
            Constructor<?> controllerConstructor = controllerClass.getDeclaredConstructor(Model.class);

            // Création d'une nouvelle instance du contrôleur en passant une instance de Model en argument du constructeur
            return (Controller) controllerConstructor.newInstance(this.model);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public View getView(String viewName, Object viewModel) {
        try {
            // Récupération de la classe de la view a partir de son nom
            Class<?> viewClass = Class.forName(viewName);

            // Recuperation du contructeur
            Constructor<?> viewConstructor = viewClass.getDeclaredConstructor(Shell.class, Object.class);

            // Creation d'un nouvelle instance de la view
            return (View) viewConstructor.newInstance(this.shell,  viewModel);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
