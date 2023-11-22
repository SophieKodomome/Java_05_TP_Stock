package myUtil;

public class EtatStock {
    String name;
    int idProduit;
    int idMagasin;
    int reste;
    double cump;
    double montant;

    public EtatStock() {
    }

    public EtatStock(String n, int iP, int iM, int r, double c, double m) {
        this.setName(n);
        this.setIDProduit(iP);
        this.setIDMagasin(iM);
        this.setReste(r);
        this.setCump(c);
        this.setMontant(m);
    }

    public void setName(String n) {
        this.name = n;
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

    public void setCump(double c) {
        this.cump = c;
    }

    public void setMontant(double m) {
        this.montant = m;
    }

    public String getName() {
        return this.name;
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

    public double getCump() {
        return this.cump;
    }

    public double getMontant() {
        return this.montant;
    }
}
