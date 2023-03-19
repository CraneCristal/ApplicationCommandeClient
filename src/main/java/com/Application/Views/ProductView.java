package com.Application.Views;

import com.Application.Controllers.OrderDetailsController;
import com.Application.ViewModels.ProductViewModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class ProductView extends View{
    Button backOrderButton;
    public ProductView(Shell shell, Object viewModel) {
        super(shell, viewModel);
    }

    public void show() {
        this.shell.setSize(500, 400);
        // Nettoyage de la fenêtre avant de recréer l'affichage
        for (Control control : this.shell.getChildren()) {
            control.dispose();
        }

        this.backOrderButton = new Button(this.shell, SWT.PUSH);
        this.backOrderButton.setText("Retour à la commande");
        this.backOrderButton.setBounds(75, 300, 200, 25);
        this.backOrderButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                controllerCall = new ControllerCall(OrderDetailsController.class, ((ProductViewModel)viewModel).orderId);
            }
        });

        Label productId = new Label(this.shell, SWT.NONE);
        productId.setText("ID : " + ((ProductViewModel)this.viewModel).productId);
        productId.setBounds(50, 50, 400, 25);

        Label productName = new Label(this.shell, SWT.NONE);
        productName.setText("Nom : " + ((ProductViewModel)this.viewModel).productName);
        productName.setBounds(50, 80, 400, 25);

        Label productCategory = new Label(this.shell, SWT.NONE);
        productCategory.setText("Catégorie : " + ((ProductViewModel)this.viewModel).productCategory);
        productCategory.setBounds(50, 110, 400, 25);

        Label productStandardCost = new Label(this.shell, SWT.NONE);
        productStandardCost.setText("Prix standard : " + ((ProductViewModel)this.viewModel).standardCost);
        productStandardCost.setBounds(50, 140, 400, 25);

        Label productQuantity = new Label(this.shell, SWT.NONE);
        productQuantity.setText("Quantité par untité : " + ((ProductViewModel)this.viewModel).productQuantityPerUnit);
        productQuantity.setBounds(50, 170, 400, 25);
    }

    public void update() {

    }
}
