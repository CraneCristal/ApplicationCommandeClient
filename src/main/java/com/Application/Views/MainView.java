package com.Application.Views;

import com.Application.Controllers.CustomerEditController;
import com.Application.Controllers.MainController;
import com.Application.Controllers.OrderDetailsController;
import com.Application.ViewModels.MainViewModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

public class MainView extends View {
    //Elements graphique
    private Table ordersTable;
    private Combo combo_customers;
    private Button editButton;
    private Button orderDetailButton;

    public MainView(Shell shell, Object viewModel) {
        super(shell, viewModel);
    }

    public void show() {
        this.shell.setSize(800, 600);
        // Nettoyage de la fenêtre avant de recréer l'affichage
        for (Control control : this.shell.getChildren()) {
            control.dispose();
        }

        // Mise en place du nouvel affichage
        // Tableau des commande du client selectioné
        this.ordersTable = new Table(this.shell, SWT.BORDER | SWT.READ_ONLY);
        this.ordersTable.setHeaderVisible(true);
        String[] columnTitles = new String[]{"Numero de commande", "Date", "Client", "Total"};
        for (String title : columnTitles) {
            TableColumn column = new TableColumn(this.ordersTable, SWT.NONE);
            column.setText(title);
        }
        this.update();
        //Redimension automatique des colonnes
        for (int i = 0; i < ordersTable.getColumnCount(); i++) {
            this.ordersTable.getColumn(i).pack();
        }
        this.ordersTable.setBounds(25, 70, 725, 400);

        // Liste deroulante des clients
        this.combo_customers = new Combo(this.shell, SWT.READ_ONLY);
        this.combo_customers.setItems(((MainViewModel) this.viewModel).customersName);
        this.combo_customers.setBounds(25, 25, 500, 50);
        this.combo_customers.select(0);
        this.combo_customers.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // Récupération du nom du client sélectionné
                int selectedCustomer = combo_customers.getSelectionIndex();
                // Création d'un nouveau ControllerCall et retour
                controllerCall = new ControllerCall(MainController.class, selectedCustomer);
            }
        });

        // Bouton d'edition du client selectionné
        this.editButton = new Button(this.shell, SWT.PUSH);
        this.editButton.setText("Editer");
        this.editButton.setBounds(550, 25, 100, 30);
        this.editButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                controllerCall = new ControllerCall(CustomerEditController.class, combo_customers.getText());
            }
        });

        // Bouton d'acces au detail de la commande
        this.orderDetailButton = new Button(this.shell, SWT.PUSH);
        this.orderDetailButton.setText("Detail commande");
        this.orderDetailButton.setBounds(600, 490, 150,30);
        this.orderDetailButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if(!(ordersTable.getSelection().length == 0)) {
                    TableItem selectedItem = ordersTable.getSelection()[0];
                    // On appel un controlleur avec comme arguent l'id de la commande
                    controllerCall = new ControllerCall(OrderDetailsController.class, selectedItem.getText(0));
                }
            }
        });
    }

    public void actualiseTable(Table table, MainViewModel mainViewModel) {
        table.removeAll();
        for (String[] line : mainViewModel.ordersCurrentCustomer) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(line);
        }
    }

    public void update() {
        if(!this.ordersTable.isDisposed()) {
            String[][] lines = ((MainViewModel)this.viewModel).ordersCurrentCustomer;
            TableItem[] items = this.ordersTable.getItems();
            if(lines.length < items.length) {
                this.ordersTable.remove(lines.length, items.length-1);
            }
            for(int i = 0; i<lines.length; i++) {
                TableItem item;
                if(i<items.length) {
                    item = items[i];
                }
                else {
                    item = new TableItem(this.ordersTable, SWT.NONE);
                }
                item.setText(lines[i]);
            }
        }

    }
}
