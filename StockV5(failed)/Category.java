package marchandise;

public class Category {
    int id;
    String name;
    public Category(){}
    
    public Category(int i, String n){
        this.setID(i);
        this.setName(n);
    }

    public void setID(int i){
        this.id=i;
    }

    public void setName(String n){
        this.name=n;
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
