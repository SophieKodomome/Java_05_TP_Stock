package marchandise;

public class Produit {
    int id;
    String name;
    int idCategory;

    public Produit() {
    }

    public Produit(int i, String n, int iC) {
        this.setID(i);
        this.setName(n);
        this.setIdCategory(iC);
    }

    public void setID(int i) {
        this.id = i;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setIdCategory(int iC) {
        this.idCategory = iC;
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getIdCategory() {
        return this.idCategory;
    }
}