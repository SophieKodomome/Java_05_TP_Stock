package transaction;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Stock {
    int id;
    int idProduit;
    int idDepot;
    int QEntry;
    int QExit;
    int currentStock;
    double cump; // coup Unitaire Moyen Pandere
    // LocalDate dateLocal;
    Date dateStock;

    public Stock() {
    }

    public Stock(int i, int iP, int qEn, int qEx, int iD, int s, Date d, double c) {
        // this.dateLocal = LocalDate.now();
        // DateTimeFormatter dateTempsFormatteur =
        // DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        // String dateFormateString = dateLocal.format(dateTempsFormatteur);
        this.setID(i);
        this.setIDProduit(iP);
        this.setQEntry(qEn);
        this.setQExit(qEx);
        this.setIDDepot(iD);
        this.setCurrentStock(s);
        this.setDateStock(d);
        this.setCump(c);
    }

    public void setID(int i) {
        this.id = i;
    }

    public void setIDProduit(int iP) {
        this.idProduit = iP;
    }

    public void setQEntry(int q) {
        this.QEntry = q;
    }

    public void setQExit(int q) {
        this.QEntry = q;
    }

    public void setIDDepot(int iD) {
        this.idDepot = iD;
    }

    public void setCurrentStock(int s) {
        this.currentStock = s;
    }

    public void setDateStock(Date d) {
        this.dateStock = d;
    }

    public void setCump(double c) {
        this.cump = c;
    }

    public int getID() {
        return this.id;
    }

    public int getIDProduit() {
        return this.idProduit;
    }

    public int getQEntry() {
        return this.QEntry;
    }

    public int getQExit() {
        return this.QExit;
    }

    public int getIDDepot() {
        return this.idDepot;
    }

    public int getCurrentStock() {
        return this.currentStock;
    }

    public double getCump() {
        return this.cump;
    }

    public Date getDateStock() {
        return this.dateStock;
    }
}
