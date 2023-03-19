package com.Application.Controllers;

import com.Application.Model.Customer;
import com.Application.Model.Model;
import com.Application.Model.Order;
import com.Application.ViewModels.MainViewModel;

public class MainController extends Controller{

    public MainController(Model model) {
        super(model);
    }
    public ControllerResult run(Object ... args) {
        // Recuperation du client dont les commande vont etre affichée
        int customerPos;
        if(args.length == 0)
            customerPos = 0;
        else
            customerPos = (int) args[0];

        // Creation de la liste des noms des clients
        Customer[] customers = this.model.getAllCustommers();
        String[] customersNames = new String[customers.length];
        for(int i = 0; i<customers.length; i++) {
            customersNames[i] = customers[i].id;
        }

        // Creation de la liste des information des commandes du client à afficher
        Order[] ordersCustomer = this.model.getCustomerOrders(customers[customerPos].id);
        String[][] stringsOrdersCustomer = new String[ordersCustomer.length][];
        for(int i = 0; i<ordersCustomer.length; i++) {
            stringsOrdersCustomer[i] = new String[]{ordersCustomer[i].id, ordersCustomer[i].date, ordersCustomer[i].customer_id, ordersCustomer[i].fullPrice};
        }

        MainViewModel viewModel = new MainViewModel(customersNames, stringsOrdersCustomer);
        return new ControllerResult("MainView", viewModel);
    }
}
