package connexion;

import java.sql.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

import marchandise.*;
import myUtil.*;
import transaction.Mouvement;

public class DBCon {
    public static ArrayList<Magasin> getListMagasin() {
        ArrayList<Magasin> magasinList = new ArrayList<>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");

            Statement stmnt = con.createStatement();

            ResultSet resultatMagasin = stmnt.executeQuery("SELECT * FROM Magasin");

            while (resultatMagasin.next()) {
                Magasin magasin = new Magasin();
                magasin.setID(resultatMagasin.getInt("Id"));
                magasin.setName(resultatMagasin.getString("nom"));
                magasinList.add(magasin);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return magasinList;
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

    public static ArrayList<Mouvement> getListMouvement() {
        ArrayList<Mouvement> mouvementList = new ArrayList<>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");

            Statement stmnt = con.createStatement();

            ResultSet resultatMouvement = stmnt.executeQuery("SELECT * FROM Mouvement");

            while (resultatMouvement.next()) {

                Mouvement mouvement = new Mouvement();
                mouvement.setIDProduit(resultatMouvement.getInt("idProduit"));
                mouvement.setIDCategory(resultatMouvement.getInt("idCategory"));
                mouvement.setIDMagasin(resultatMouvement.getInt("idDepot"));
                mouvement.setEntree(resultatMouvement.getInt("entree"));
                mouvement.setSortie(resultatMouvement.getInt("sortie"));
                mouvement.setMoney(resultatMouvement.getInt("money"));
                mouvement.setDateStock(resultatMouvement.getDate("datestock"));
                mouvementList.add(mouvement);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mouvementList;
    }

    public static ArrayList<marchandise.Category> getListCategory() {
        ArrayList<marchandise.Category> categoryList = new ArrayList<>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");

            Statement stmnt = con.createStatement();

            ResultSet resultatCategory = stmnt.executeQuery("SELECT * FROM Category");

            while (resultatCategory.next()) {
                marchandise.Category category = new marchandise.Category();
                category.setID(resultatCategory.getInt("Id"));
                category.setName(resultatCategory.getString("nom"));
                categoryList.add(category);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    public static ArrayList<EtatStock> getListEtatStock() {
        ArrayList<EtatStock> etatStockList = new ArrayList<>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");

            Statement stmnt = con.createStatement();

            ResultSet resultatEtatStock = stmnt.executeQuery(
                    "select idProduit,idMagasin,(SUM(Entree)-SUM(Sortie)) AS Reste,((SUM(Entree * Money)/SUM(Entree))*(SUM(Entree)-SUM(Sortie))) AS Montant from Mouvement GROUP BY idProduit,idMagasin");

            while (resultatEtatStock.next()) {
                EtatStock etatStock = new EtatStock();
                etatStock.setIDProduit(resultatEtatStock.getInt("IdProduit"));
                etatStock.setIDMagasin(resultatEtatStock.getInt("IdMagasin"));
                etatStock.setReste(resultatEtatStock.getInt("Reste"));
                etatStock.setMontant(resultatEtatStock.getInt("Montant"));
                etatStockList.add(etatStock);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return etatStockList;
    }

    public static ArrayList<EtatCategory> getListEtatCategory() {
        ArrayList<EtatCategory> etatStockList = new ArrayList<>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");

            Statement stmnt = con.createStatement();

            ResultSet resultatEtatCategory = stmnt.executeQuery(
                    "select \r\n" + //
                            "category.nom,\r\n" + //
                            "idCategory,\r\n" + //
                            "idMagasin,\r\n" + //
                            "((SUM(Entree * Money)/SUM(Entree))*(SUM(Entree)-SUM(Sortie))) AS Montant \r\n" + //
                            "from Mouvement \r\n" + //
                            "INNER JOIN category ON mouvement.idCategory=category.id \r\n" + //
                            "GROUP BY category.nom,idCategory,idMagasin");

            while (resultatEtatCategory.next()) {
                EtatCategory etatStock = new EtatCategory();
                etatStock.setName(resultatEtatCategory.getString("nom"));
                etatStock.setIDCategory(resultatEtatCategory.getInt("IdCategory"));
                etatStock.setIDMagasin(resultatEtatCategory.getInt("IdMagasin"));
                etatStock.setMontant(resultatEtatCategory.getDouble("Montant"));
                etatStockList.add(etatStock);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return etatStockList;
    }

    public static void UpdateMouvement(Mouvement mouvement) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");

            Statement stmnt = con.createStatement();

            stmnt.executeUpdate("INSERT INTO Mouvement VALUES(mouvementIdSeq.NEXTVAL," + mouvement.getIDMagasin()
                    + "," + mouvement.getIDProduit() + "," + mouvement.getIDCategory() + ","
                    + mouvement.getEntree() + "," + mouvement.getSortie() + "," + mouvement.getMoney()
                    + ",SYSDATE)");

            stmnt.executeUpdate("commit");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
