package com.Application.Model;

import java.util.Objects;

public class Model {
    // Retour de la liste des clients de la base de donnée
    public Customer[] getAllCustommers() {
        Customer nat = new Customer("1254", "Nathan", "DELOBEL");
        Customer ant = new Customer("2784", "Antoine", "JOLY");
        return new Customer[]{nat, ant};
    }

    // Retour de la liste des commandes pour un client
    public Order[] getCustomerOrders(String customerId) {
        Order order1;
        Order order2;
        if(customerId.equals("1254")) {
            order1 = new Order("161", "12/01/2016", "1254", "67€");
            order2 = new Order("191", "12/01/2019", "1254", "27€");
        }
        else {
            order1 = new Order("154", "12/04/2012", "2784", "67€");
            order2 = new Order("195", "12/01/2022", "2784", "27€");
        }
        return new Order[] {order1, order2};
    }

    public OrderDetail[] getOrderDetail(String orderId, String... args) {
        if(orderId.equals("161")) {
            return new OrderDetail[] {new OrderDetail("123", "3", "3€", "0%"), new OrderDetail("234", "4", "555€", "20%")};
        }
        else
            return new OrderDetail[] {new OrderDetail("243", "10", "1€", "70%"), new OrderDetail("24", "55", "5€", "2%")};
    }
}
