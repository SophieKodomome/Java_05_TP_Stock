package transaction;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Stock {
    int id;
    int idProduit;
    int quantite;
    int stock;
    int cump; // coup Unitaire Moyen Pandere
    LocalDate dateLocal;

    public Stock(int i, int iP, int q, int s) {
        dateLocal = LocalDate.now();
        DateTimeFormatter dateTempsFormatteur = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        String dateFormate = dateLocal.format(dateTempsFormatteur);

        this.setID(i);
        this.setIDProduit(iP);
        this.setQuantite(q);
        this.setStock(s);
        // this.setCump();
    }

    public void setID(int i) {
        this.id = i;
    }

    public void setIDProduit(int iP) {
        this.idProduit = iP;
    }

    public void setQuantite(int q) {
        this.quantite = q;
    }

    public void setStock(int s) {
        this.stock = s;
    }
}
