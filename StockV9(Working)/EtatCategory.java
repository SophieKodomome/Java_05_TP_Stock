package myUtil;

public class EtatCategory {
    String name;
    int idProduit;
    int idCategory;;
    double montant;

    public EtatCategory() {
    }

    public EtatCategory(String n, int iM, int iC, double m) {
        this.setName(n);
        this.setIDMagasin(iM);
        this.setIDCategory(iC);
        this.setMontant(m);
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setIDMagasin(int iM) {
        this.idProduit = iM;
    }

    public void setIDCategory(int iC) {
        this.idCategory = iC;
    }

    public void setMontant(double m) {
        this.montant = m;
    }

    public String getName() {
        return this.name;
    }

    public int getIDMagasin() {
        return this.idProduit;
    }

    public int getIDCategory() {
        return this.idCategory;
    }

    public double getMontant() {
        return this.montant;
    }
}
