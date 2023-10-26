package mainpackage;

public class Item {

    private int id;
    private String name;
    private float price;
    private float weight;
    private int available;
    private String description;
    private boolean favourite;

    public Item(int id, String name, float price, float weight, int available, String description, boolean favourite) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.available = available;
        this.description = description;
        this.favourite = favourite;
    }

    private void select(){

    }
    private void favor(){
        favourite = true;
    }
    private void showDetails(){

    }
}
