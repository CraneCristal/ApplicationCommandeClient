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
        String[] customersId = new String[customers.length];
        for(int i = 0; i<customers.length; i++) {
            customersId[i] = customers[i].id;
        }

        // Creation de la liste des information des commandes du client à afficher
        Order[] ordersCustomer = this.model.getCustomerOrders(customers[customerPos].id);
        Customer concernedCustomer = this.model.getCustomer(ordersCustomer[0].customer_id);
        String[][] stringsOrdersCustomer = new String[ordersCustomer.length][];
        for(int i = 0; i<ordersCustomer.length; i++) {
            stringsOrdersCustomer[i] = new String[]{ordersCustomer[i].id, ordersCustomer[i].date, concernedCustomer.first_name + " " + concernedCustomer.last_name, ordersCustomer[i].fullPrice};
        }

        MainViewModel viewModel = new MainViewModel(customersId, stringsOrdersCustomer);
        return new ControllerResult("MainView", viewModel);
    }
}
