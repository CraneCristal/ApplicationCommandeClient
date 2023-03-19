package com.Application.Views;

import com.Application.Controllers.MainController;
import com.Application.Controllers.OrderDetailsController;
import com.Application.Controllers.ProductController;
import com.Application.ViewModels.OrderDetailsViewModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

public class OrderDetailsView extends View{
    private Table orderDetailTable;
    private Button customerListButton;

    private Button productDetailButton;
    public OrderDetailsView(Shell shell, Object viewModel) {
        super(shell, viewModel);
    }

    public void show() {
        this.shell.setSize(800, 600);
        // Nettoyage de la fenêtre avant de recréer l'affichage
        for (Control control : this.shell.getChildren()) {
            control.dispose();
        }

        this.orderDetailTable = new Table(this.shell, SWT.BORDER | SWT.READ_ONLY);
        this.orderDetailTable.setHeaderVisible(true);
        String[] columnTitles = new String[]{"Produit", "Quantité", "Prix Unitaire", "Réduction"};
        for (String title : columnTitles) {
            TableColumn column = new TableColumn(this.orderDetailTable, SWT.NONE);
            column.setText(title);
        }
        //Redimension automatique des colonnes
        for (int i = 0; i < this.orderDetailTable.getColumnCount(); i++) {
            this.orderDetailTable.getColumn(i).pack();
        }
        this.orderDetailTable.setBounds(25, 25, 725, 450);
        this.update();

        this.orderDetailTable.getColumn(0).addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                controllerCall = new ControllerCall(OrderDetailsController.class, ((OrderDetailsViewModel)viewModel).customerId, "Produit");
            }
        });
        this.orderDetailTable.getColumn(1).addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                controllerCall = new ControllerCall(OrderDetailsController.class, ((OrderDetailsViewModel)viewModel).customerId, "Quantité");
            }
        });
        this.orderDetailTable.getColumn(2).addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                controllerCall = new ControllerCall(OrderDetailsController.class, ((OrderDetailsViewModel)viewModel).customerId, "Prix Unitaire");
            }
        });

        this.orderDetailTable.getColumn(3).addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                controllerCall = new ControllerCall(OrderDetailsController.class, ((OrderDetailsViewModel)viewModel).customerId, "Reduction");
            }
        });


        this.customerListButton = new Button(this.shell, SWT.PUSH);
        this.customerListButton.setBounds(600, 490, 150,30);
        this.customerListButton.setText("Liste des clients");
        this.customerListButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                controllerCall = new ControllerCall(MainController.class);
            }
        });

        this.productDetailButton = new Button(this.shell, SWT.PUSH);
        this.productDetailButton.setBounds(200, 490, 150,30);
        this.productDetailButton.setText("Detail produit");
        this.productDetailButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if(orderDetailTable.getSelection().length != 0) {
                    TableItem selectedItem = orderDetailTable.getSelection()[0];
                    controllerCall = new ControllerCall(ProductController.class, selectedItem.getText(0), ((OrderDetailsViewModel)viewModel).customerId);
                }
            }
        });
    }

    public void update() {
        if(!this.orderDetailTable.isDisposed()) {
            this.orderDetailTable.removeAll();
            for (String[] line : ((OrderDetailsViewModel)this.viewModel).details) {
                TableItem item = new TableItem(this.orderDetailTable, SWT.NONE);
                item.setText(line);
            }
        }
    }
}
