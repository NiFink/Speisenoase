package mainpackage.itempackage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Grocery implements Item{

    private int id;
    private String name;
    private double price;
    private String category;
    private long available;
    private boolean favourite;
    private String description;

    ObjectMapper objectMapper = new ObjectMapper();

    Grocery createGrocery(int id) {
        return getItemlist().get(id);
    }

    private List<Grocery> getItemlist(){
        try{
            return  objectMapper.readValue(new File("src/main/resources/itemData.json"), new TypeReference<List<Grocery>>() {});
        } catch(IOException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {return name; }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public long getAvailable() {
        return available;
    }

    public void setAvailable(long available) {
        this.available = available;
    }

    public boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item {" +
                "\n  id=" + id +
                ",\n name='" + name +
                ",\n price=" + price +
                ",\n category=" + category +
                ",\n available=" + available +
                ",\n favourite=" + favourite +
                ",\n description='" + description +
                ",\n}";
    }
}
