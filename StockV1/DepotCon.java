package connexion;

import java.sql.*;
import java.util.*;

import marchandise.*;

public class DepotCon {
    public static ArrayList<Depot> getListDepot() {
        ArrayList<Depot> depotList = new ArrayList<>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver Succesful");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
            System.out.println("Connection Succesful");

            Statement stmnt = con.createStatement();

            ResultSet resultatDepot = stmnt.executeQuery("SELECT * FROM Depot");

            while (resultatDepot.next()) { // problems stem from here
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
}
