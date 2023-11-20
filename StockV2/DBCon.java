package connexion;

import java.sql.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

import marchandise.*;
import transaction.Stock;

public class DBCon {
    public static ArrayList<Depot> getListDepot() {
        ArrayList<Depot> depotList = new ArrayList<>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");

            Statement stmnt = con.createStatement();

            ResultSet resultatDepot = stmnt.executeQuery("SELECT * FROM Depot");

            while (resultatDepot.next()) {
                Depot depot = new Depot();
                depot.setID(resultatDepot.getInt("Id"));
                depot.setName(resultatDepot.getString("nom"));
                depot.setIDProduit(resultatDepot.getInt("idProduit"));
                depot.setprixDepot(resultatDepot.getInt("price"));
                depotList.add(depot);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return depotList;
    }

    public static ArrayList<String> getListDepotName() {
        ArrayList<String> depotListName = new ArrayList<>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");

            Statement stmnt = con.createStatement();

            ResultSet resultatDepot = stmnt.executeQuery("select nom from Depot group by Nom");

            while (resultatDepot.next()) {
                depotListName.add(resultatDepot.getString("nom"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return depotListName;
    }

    public static ArrayList<Produit> getListProduit() {
        ArrayList<Produit> produitList = new ArrayList<>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");

            Statement stmnt = con.createStatement();

            ResultSet resultatProduit = stmnt.executeQuery("SELECT * FROM Produit");

            while (resultatProduit.next()) {
                Produit produit = new Produit();
                produit.setID(resultatProduit.getInt("Id"));
                produit.setName(resultatProduit.getString("nom"));
                produitList.add(produit);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return produitList;
    }

    public static ArrayList<Stock> getListStock() {
        ArrayList<Stock> stockList = new ArrayList<>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");

            Statement stmnt = con.createStatement();

            ResultSet resultatStock = stmnt.executeQuery("SELECT * FROM Stock");

            while (resultatStock.next()) {
                Stock stock = new Stock();
                stock.setID(resultatStock.getInt("id"));
                stock.setIDProduit(resultatStock.getInt("idPrduit"));
                stock.setQEntry(resultatStock.getInt("QtEntry"));
                stock.setQExit(resultatStock.getInt("QtExit"));
                stock.setIDDepot(resultatStock.getInt("idDepot"));
                stock.setDateStock(resultatStock.getDate("dateStock"));
                stock.setCump(resultatStock.getDouble("id"));
                stockList.add(stock);
            }
            if (stockList.isEmpty()) {
                LocalDate dateLocal = LocalDate.now();
                Instant instant = dateLocal.atTime(LocalTime.now()).atZone(ZoneId.systemDefault()).toInstant();
                java.util.Date date = java.util.Date.from(instant);
                stockList.add(new Stock(0, 0, 0, 0, 0, 0, date, 0));
                // System.out.println(stockList.get(0).getDateStock());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stockList;

    }
}
