package mainpackage.itempackage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Grocery implements Item{

    private int id;
    private String name;
    private double price;
    private String category;

    private final JsonReader jsonReader = JsonReader.getInstance();
    private static final Logger log = LogManager.getLogger(Grocery.class);

    /**
     * Gets grocery from an itemlist on the basis of a given id.
     * @param id of the wanted Item
     * @return Grocery
     */
    Grocery createGrocery(int id) {
        Grocery grocery = jsonReader.getItemList().get(id);
        log.debug("Grocery with id: " + id + " is returned from Json List");
        return grocery;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory(){
        return category;
    }

    @Override
    public String toString() {
        return "Item {" +
                "\n  id=" + id +
                ",\n name='" + name +
                ",\n price=" + price +
                ",\n category=" + category +
                ",\n}";
    }
}
