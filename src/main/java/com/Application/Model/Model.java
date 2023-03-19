package com.Application.Model;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.sql.*;

public class Model {

    public Model() {
        Connection();
    }

    // Retour de la liste des clients de la base de donnée
    public Customer[] getAllCustommers() {
        String url = "jdbc:h2:./h2database";
        List<Customer> customersList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();

            String requete="SELECT ID, LASTNAME, FIRSTNAME FROM Customers";
            ResultSet rs = stmt.executeQuery(requete);

            // Itération sur les résultats de la requête et création d'un objet Customers pour chaque client
            while (rs.next()) {
                Customer customer = new Customer(rs.getString("ID"),rs.getString("FIRSTNAME"),rs.getString("LASTNAME"));
                customersList.add(customer);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customersList.toArray(Customer[]::new) ;
    }

    // Retour de la liste des commandes pour un client
    public Order[] getCustomerOrders(String customerId) {
        String url = "jdbc:h2:./h2database";
        List<Order> OrderList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT o.OrderID, o.OrderDate, o.CustomerID, SUM(CAST(REPLACE(od.UnitPrice, ',', '.') AS DECIMAL(10, 2)) * CAST(od.Quantity AS DECIMAL(10, 2)) * (100 - CAST(REPLACE(od.Discount, ',', '.') AS DECIMAL(10, 2))) / 100) AS TotalPrice FROM Orders o JOIN OrderDetails od ON o.OrderID = od.OrderID WHERE o.CustomerID="+customerId+" GROUP BY o.OrderID"); ){
            // Itération sur les résultats de la requête et création d'un objet Order pour chaque client
            while (rs.next()) {
                Order order = new Order(rs.getString("OrderID"), rs.getString("OrderDate"), rs.getString("CustomerID"), rs.getString("TotalPrice"));
                OrderList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return OrderList.toArray(Order[]::new);
    }

    //Retourne tous les details concernant une commande
    public OrderDetail[] getOrderDetail(String orderId, String... args) {
        String url = "jdbc:h2:./h2database";
        List<OrderDetail> OrderDetailList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();

            String requete="SELECT ProductID, Quantity, UnitPrice, Discount FROM OrderDetails";
            ResultSet rs = stmt.executeQuery(requete);

            // Itération sur les résultats de la requête et création d'un objet OrderDetail pour chaque client
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail(rs.getString("ProductID"),rs.getString("Quantity"),rs.getString("UnitPrice"),rs.getString("Discount"));
                OrderDetailList.add(orderDetail);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return OrderDetailList.toArray(OrderDetail[]::new) ;
    }

    //Etablie la connection avec la base de données H2 et notre programme
    private static void Connection(){
        String[] filepaths = {
                "Customers.csv",
                "Employees.csv",
                "OrderDetails.csv",
                "Orders.csv",
                "Products.csv"
        };

        Connection conn = null;
        try {
            // Connexion à la base de données H2
            String url = "jdbc:h2:./h2database";
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            // Boucle sur les fichiers CSV
            for (String filepath : filepaths) {
                InputStream inputStream = Model.class.getClassLoader().getResourceAsStream(filepath);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String header = reader.readLine();
                String[] columnNames = header.split(";");

                String[] columnTypes = new String[columnNames.length];
                for (int i = 0; i < columnTypes.length; i++) {
                    columnTypes[i] = "VARCHAR(255)";
                }

                String tableName = getTableName(filepath);

                String createTableSql = "CREATE TABLE " + tableName + " (" + getColumnsStatement(columnNames, columnTypes) + ")";
                stmt.execute(createTableSql);
                String line;

                while ((line = reader.readLine()) != null) {

                    String[] values = line.split(";");

                    String[] sqlValues = new String[columnNames.length];
                    for (int i = 0; i < columnNames.length; i++) {

                        String value = (i < values.length && !values[i].isEmpty()) ? values[i].trim() : "VIDE";
                        sqlValues[i] = "'" + value + "'";
                    }
                    stmt.execute("INSERT INTO " + tableName + " VALUES (" + String.join(",", sqlValues) + ")");
                }
                reader.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Méthode pour obtenir le nom de la table à partir du chemin d'accès du fichier CSV
    private static String getTableName(String filepath) {
        int startIndex = filepath.lastIndexOf("/") + 1;
        int endIndex = filepath.lastIndexOf(".");
        return filepath.substring(startIndex, endIndex);
    }

    // Méthode pour générer la partie "COLONNE1 TYPE1, COLONNE2 TYPE2, ..." de la requête SQL CREATE TABLE
    private static String getColumnsStatement(String[] columnNames, String[] columnTypes) {
        StringBuilder sb = new StringBuilder();
        if (columnNames != null && columnTypes != null) {
            // Si des noms de colonnes et des types ont été fournis, les ajouter à la chaîne de caractères
            for (int i = 0; i < columnNames.length; i++) {
                sb.append(columnNames[i].replaceAll("[^a-zA-Z0-9]", "") + " " + columnTypes[i]);
                if (i < columnNames.length - 1) {
                    sb.append(", ");
                }
            }
        }
        if (columnNames != null && columnTypes == null) {
            // Si seuls des noms de colonnes ont été fournis, les ajouter à la chaîne de caractères
            for (int i = 0; i < columnNames.length; i++) {
                sb.append(columnNames[i].replaceAll("[^a-zA-Z0-9]", ""));
                if (i < columnNames.length - 1) {
                    sb.append(", ");
                }
            }
        }
        // Retourne la chaîne de caractères résultante
        return sb.toString();
    }

    public Customer getCustomer(String customerId) {
        return new Customer("1234", "Nathan", "Delobel");
    }
}