package transaction;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Stock {
    int id;
    int idProduit;
    int idDepot;
    int IdCategory;
    // int movementQuantite;
    int currentStock;
    double cump; // coup Unitaire Moyen Pandere
    // LocalDate dateLocal;
    // Date dateStock;

    public Stock() {
    }

    public Stock(int iP, int iC, int iD, int s, double c) {
        // this.dateLocal = LocalDate.now();
        // DateTimeFormatter dateTempsFormatteur =
        // DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        // String dateFormateString = dateLocal.format(dateTempsFormatteur);
        this.setIDProduit(iP);
        // this.setMovementQuantite(q);
        this.setIDDepot(iD);
        this.setIDCategory(iC);
        this.setCurrentStock(s);
        // this.setDateStock(d);
        this.setCump(c);
    }

    public void setIDProduit(int iP) {
        this.idProduit = iP;
    }

    public void setIDCategory(int iC) {
        this.IdCategory = iC;
    }

    /*
     * public void setMovementQuantite(int q) {
     * this.movementQuantite = q;
     * }
     */

    public void setIDDepot(int iD) {
        this.idDepot = iD;
    }

    public void setCurrentStock(int s) {
        this.currentStock = s;
    }

    /*
     * public void setDateStock(Date d) {
     * this.dateStock = d;
     * }
     */

    public void setCump(double c) {
        this.cump = c;
    }

    public int getIDProduit() {
        return this.idProduit;
    }

    public int getIDCategory() {
        return this.IdCategory;
    }

    /*
     * public int getMovementQuantite() {
     * return this.movementQuantite;
     * }
     */

    public int getIDDepot() {
        return this.idDepot;
    }

    public int getCurrentStock() {
        return this.currentStock;
    }

    public double getCump() {
        return this.cump;
    }

    /*
     * public Date getDateStock() {
     * return this.dateStock;
     * }
     */
}
