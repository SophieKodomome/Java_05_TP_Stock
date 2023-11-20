package myUtil;

public class EtatCategory {
    String name;
    int id;
    double valeur;

    public EtatCategory() {
    }

    public EtatCategory(String n, int id, double v) {
        this.setName(n);
        this.setID(id);
        this.setValeur(v);
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setValeur(double v) {
        this.valeur = v;
    }

    public String getName() {
        return this.name;
    }

    public int getID() {
        return this.id;
    }

    public double getValeur() {
        return this.valeur;
    }
}
