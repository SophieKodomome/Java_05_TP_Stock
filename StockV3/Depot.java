package marchandise;

public class Depot {
    int id;
    String name;
    int idProduit;
    int prixDepot;

    public Depot() {
    }

    public Depot(int i, String n, int iP, int p) {
        this.setID(i);
        this.setName(n);
        this.setIDProduit(iP);
        this.setprixDepot(p);
    }

    public void setID(int i) {
        this.id = i;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setIDProduit(int iP) {
        this.idProduit = iP;
    }

    public void setprixDepot(int p) {
        this.prixDepot = p;
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getIDProduit() {
        return this.idProduit;
    }

    public int getprixDepot() {
        return this.prixDepot;
    }
}
