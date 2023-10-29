package mainpackage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Item {

    private long id;
    private String name;
    private double price;
    private double weight;
    private long available;
    private String description;
    private boolean favourite;

    public static void main(String[] args) throws IOException, ParseException {
        Item item = new Item("apfel");
        System.out.println(item.toString());
    }

    public Item(String name) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject itemData = (JSONObject) parser.parse(new FileReader("src/main/resources/itemData.json"));
        JSONObject item = (JSONObject) itemData.get(name);

        this.id = (Long) item.get("id");
        this.name = (String) item.get("name");
        this.price = (Double) item.get("price");
        this.weight = (Double) item.get("weight");
        this.available = (Long) item.get("available");
        this.description = (String) item.get("description");
        this.favourite = (Boolean) item.get("favourite");
    }

    private void select(){

    }
    private void favor(){
        favourite = true;
    }
    private void showDetails(){

    }

    @Override
    public String toString() {
        return "Item {" +
                "\n id=" + id +
                ",\n name='" + name + '\'' +
                ",\n price=" + price +
                ",\n weight=" + weight +
                ",\n available=" + available +
                ",\n description='" + description + '\'' +
                ",\n favourite=" + favourite +
                "\n}";
    }
}
