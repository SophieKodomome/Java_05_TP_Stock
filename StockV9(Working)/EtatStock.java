package myUtil;

public class EtatStock {
    int idProduit;
    int idMagasin;
    int reste;
    double montant;

    public EtatStock() {
    }

    public EtatStock(int iP, int iM, int r, double m) {
        this.setIDProduit(iP);
        this.setIDMagasin(iM);
        this.setReste(r);
        this.setMontant(m);
    }

    public void setIDProduit(int iP) {
        this.idProduit = iP;
    }

    public void setIDMagasin(int iM) {
        this.idMagasin = iM;
    }

    public void setReste(int r) {
        this.reste = r;
    }

    public void setMontant(double m) {
        this.montant = m;
    }

    public int getIDProduit() {
        return this.idProduit;
    }

    public int getIDMagasin() {
        return this.idMagasin;
    }

    public int getReste() {
        return this.reste;
    }

    public double getMontant() {
        return this.montant;
    }
}
