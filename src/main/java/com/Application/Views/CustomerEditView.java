package com.Application.Views;

import com.Application.Controllers.MainController;
import com.Application.ViewModels.CustomerEditViewModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

public class CustomerEditView extends View{
    Text firstName;
    Text lastName;
    Button customerListButton;

    public CustomerEditView(Shell shell, Object viewModel) {
        super(shell, viewModel);
    }

    public void show() {
        this.shell.setSize(500, 500);
        // Nettoyage de la fenêtre avant de recréer l'affichage
        for (Control control : this.shell.getChildren()) {
            control.dispose();
        }

        Label label1 = new Label(this.shell, SWT.NONE);
        label1.setText("Prenom :");
        label1.setBounds(100, 75, 100, 20);
        this.firstName = new Text(this.shell, SWT.CENTER);
        this.firstName.setText(((CustomerEditViewModel)this.viewModel).firstName);
        this.firstName.setBounds(200, 75, 150, 20);

        Label label2 = new Label(this.shell, SWT.NONE);
        label2.setText("Nom :");
        label2.setBounds(100, 130, 100, 20);
        this.lastName = new Text(this.shell, SWT.CENTER);
        this.lastName.setText(((CustomerEditViewModel)this.viewModel).lastName);
        this.lastName.setBounds(200, 130, 150, 20);

        this.customerListButton = new Button(this.shell, SWT.PUSH);
        this.customerListButton.setText("Liste des clients");
        this.customerListButton.setBounds(150, 300, 200, 40);
        this.customerListButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                controllerCall = new ControllerCall(MainController.class);
            }
        });


    }

    public void update() {

    }
}
