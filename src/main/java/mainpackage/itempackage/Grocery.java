package mainpackage.itempackage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

class Grocery implements Item{
    //TODO: Clean Code

    private int id;
    private String name;
    private double price;
    private String category;

    private static final Logger log = LogManager.getLogger(Grocery.class);
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Gets grocery from an itemlist on the basis of a given id.
     * @param id of the wanted Item
     * @return Grocery
     */
    Grocery createGrocery(int id) {
        Grocery grocery = getItemlist().get(id);
        log.debug("Grocery with id: " + id + " is returned from temporary List");
        return grocery;
    }

    /**
     * Reads Json File which contains data of items and creates list of groceries with read data
     * @return list of groceries
     */
    private List<Grocery> getItemlist(){
        try{
            List<Grocery> groceryList = objectMapper.readValue(new File("src/main/resources/json/itemData.json"), new TypeReference<List<Grocery>>() {});
            log.debug("ObjectMapper read itemData.json file and creates temporary List");
            return groceryList;
        } catch (IOException e) {
            log.error("Json file could not be read. " + e);
        }
        return null;
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
