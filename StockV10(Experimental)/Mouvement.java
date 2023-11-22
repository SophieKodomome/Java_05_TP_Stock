package transaction;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import myUtil.*;
import marchandise.Magasin;

public class Mouvement {
    int id;
    int idProduit;
    int idMagasin;
    int IdCategory;
    int entree;
    int sortie;
    int money;
    // LocalDate dateLocal;
    Date dateStock;
    NegativeValueException negativeValueException = new NegativeValueException("Negative Value not allowed");

    public Mouvement() {
    }

    public Mouvement(int iP, int iC, int iM, int e, int s, int m, Date d) throws NegativeValueException {
        // this.dateLocal = LocalDate.now();
        // DateTimeFormatter dateTempsFormatteur =
        // DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        // String dateFormateString = dateLocal.format(dateTempsFormatteur);
        try {
            this.setEntree(e);
            this.setSortie(s);
            this.setMoney(m);
        } catch (NegativeValueException eN) {
            throw eN;
        }
        this.setIDProduit(iP);
        this.setIDMagasin(iM);
        this.setIDCategory(iC);
        this.setDateStock(d);
    }

    public void setIDProduit(int iP) {
        this.idProduit = iP;
    }

    public void setIDMagasin(int iM) {
        this.idMagasin = iM;
    }

    public void setIDCategory(int iC) {
        this.IdCategory = iC;
    }

    public void setEntree(int e) throws NegativeValueException {
        if (e < 0) {
            throw negativeValueException;
        }
        this.entree = e;
    }

    public void setSortie(int s) throws NegativeValueException {
        if (s < 0) {
            throw negativeValueException;
        }
        this.sortie = s;
    }

    public void setMoney(int m) throws NegativeValueException {
        if (m < 0)
            throw negativeValueException;
        {
            this.money = m;
        }
    }

    public void setDateStock(Date d) {
        this.dateStock = d;
    }

    public int getIDProduit() {
        return this.idProduit;
    }

    public int getIDMagasin() {
        return this.idMagasin;
    }

    public int getIDCategory() {
        return this.IdCategory;
    }

    public int getEntree() {
        return this.entree;
    }

    public int getSortie() {
        return this.sortie;
    }

    public int getMoney() {
        return this.money;
    }

    public Date getDateStock() {
        return this.dateStock;
    }
}
