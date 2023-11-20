package connexion;

import java.sql.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

import marchandise.*;
import myUtil.EtatCategory;
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

            ResultSet resultatStock = stmnt.executeQuery("SELECT * FROM Stock");

            while (resultatStock.next()) {
                Stock stock = new Stock();
                stock.setIDProduit(resultatStock.getInt("idProduit"));
                stock.setIDCategory(resultatStock.getInt("idCategory"));
                stock.setIDDepot(resultatStock.getInt("idDepot"));
                stock.setCurrentStock(resultatStock.getInt("currentStock"));
                stock.setCump(resultatStock.getDouble("cump"));
                stockList.add(stock);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stockList;

    }

    public static ArrayList<marchandise.Category> getListCategory() {
        ArrayList<marchandise.Category> produitList = new ArrayList<>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");

            Statement stmnt = con.createStatement();

            ResultSet resultatProduit = stmnt.executeQuery("SELECT * FROM Category");

            while (resultatProduit.next()) {
                marchandise.Category category = new marchandise.Category();
                category.setID(resultatProduit.getInt("Id"));
                category.setName(resultatProduit.getString("nom"));
                produitList.add(category);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return produitList;
    }

    public static void updateStock(Stock stock, int newStockQuantity, Double newPrice) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");

            Statement stmnt = con.createStatement();

            stmnt.executeUpdate("UPDATE stock SET currentStock = currentStock + " + newStockQuantity + ", cump = ("
                    + newStockQuantity + " * " + newPrice
                    + " + currentStock * cump) / (currentStock + " + newStockQuantity + ") WHERE idDepot = "
                    + stock.getIDDepot() + " AND idProduit = " + stock.getIDProduit());

            stmnt.executeUpdate("commit");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateMvmt(Stock stock, int newStockQuantity, Double newPrice) {

        try {
            Stock selectedStock = new Stock();
            ArrayList<Stock> listStock = DBCon.getListStock();

            for (int i = 0; i < listStock.size(); i++) {

                if (listStock.get(i).getIDDepot() == stock.getIDDepot()
                        && listStock.get(i).getIDProduit() == stock.getIDProduit()) {
                    selectedStock = listStock.get(i);
                }
            }
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");

            Statement stmnt = con.createStatement();
            stmnt.executeUpdate(
                    "INSERT INTO mvmtstock (id, idDepot, idProduit, QtMVMT, money, currentStock, cump, dateStock) " +
                            "VALUES (stockIdSeq.NEXTVAL, " + selectedStock.getIDDepot() + ", "
                            + selectedStock.getIDProduit() + ", " +
                            newStockQuantity + ", " + newPrice + ", " + selectedStock.getCurrentStock() + ", " +
                            selectedStock.getCump() + ", SYSDATE)");

            stmnt.executeUpdate("commit");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<EtatCategory> getListEtatCategory() {
        ArrayList<EtatCategory> listEtatCategory = new ArrayList<>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");

            Statement stmnt = con.createStatement();

            ResultSet resultatEtatCategory = stmnt
                    .executeQuery("select idcategory,sum(CURRENTSTOCK *CUMP) as value from stock group by idcategory;");

            while (resultatEtatCategory.next()) {
                EtatCategory etatCategory = new EtatCategory();
                etatCategory.setID(resultatEtatCategory.getInt("idcategory"));
                etatCategory.setValeur(resultatEtatCategory.getDouble("value"));
                listEtatCategory.add(etatCategory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listEtatCategory;
    }
}
