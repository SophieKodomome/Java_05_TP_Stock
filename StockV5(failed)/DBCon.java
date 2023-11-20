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
                depotList.add(depot);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return depotList;
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
                produit.setIdCategory(resultatProduit.getInt("IdCategory"));
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

            ResultSet resultatStock = stmnt.executeQuery("SELECT * FROM mvmtStock");

            while (resultatStock.next()) {
                Stock stock = new Stock();
                stock.setIDProduit(resultatStock.getInt("idProduit"));
                stock.setMovementQuantite(resultatStock.getInt("QtMvmt"));
                stock.setIDCategory(resultatStock.getInt("idCategory"));
                stock.setIDDepot(resultatStock.getInt("idDepot"));
                stock.setCurrentStock(resultatStock.getInt("currentStock"));
                stock.setDateStock(resultatStock.getDate("dateStock"));
                stock.setCump(resultatStock.getDouble("cump"));
                stockList.add(stock);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stockList;

    }

    public static ArrayList<Category> getListCategory() {
        ArrayList<Category> produitList = new ArrayList<>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");

            Statement stmnt = con.createStatement();

            ResultSet resultatProduit = stmnt.executeQuery("SELECT * FROM Category");

            while (resultatProduit.next()) {
                Category category = new Category();
                category.setID(resultatProduit.getInt("Id"));
                category.setName(resultatProduit.getString("nom"));
                produitList.add(category);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return produitList;
    }

    public static void updateStock(Stock stock) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");

            Statement stmnt = con.createStatement();

            LocalDate dateLocal = LocalDate.now();
            DateTimeFormatter dateTempsFormatteur = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
            String dateFormate = dateLocal.format(dateTempsFormatteur);

            stmnt.executeUpdate(
                    "INSERT INTO mvmtstock VALUES('"
                            + stock.getIDDepot() + "','"
                            + stock.getIDProduit() + "','"
                            + stock.getIDCategory() + "','"
                            + stock.getMovementQuantite() + "','"
                            + stock.getCurrentStock() + "','"
                            + stock.getCump() + "','"
                            + dateFormate + "')");
            stmnt.executeUpdate("commit");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
