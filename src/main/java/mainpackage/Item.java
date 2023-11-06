package mainpackage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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

    JSONParser parser = new JSONParser();
    JSONObject itemData = (JSONObject) parser.parse(new FileReader("src/main/resources/itemData.json"));

    public static void main(String[] args) throws IOException, ParseException {
        Item item = new Item("banane");
        System.out.println(item.toString());
        item.favor();
        item.updateData();
        System.out.println(item.toString());
    }

    /**
     * Konstruktor erstellt Item anhand des Parameter name und holt sich die dazugehörigen Daten aus der itemData.json Datei
     * @param name Name des Items das erstellt wird
     */
    public Item(String name) throws IOException, ParseException {
        JSONObject item = (JSONObject) itemData.get(name);

        this.id = (Long) item.get("id");
        this.name = (String) item.get("name");
        this.price = (Double) item.get("price");
        this.weight = (Double) item.get("weight");
        this.available = (Long) item.get("available");
        this.description = (String) item.get("description");
        this.favourite = (Boolean) item.get("favourite");
    }

    private void select(){    }

    /**
     * Inverts the boolean favourite
     */
    private void favor(){
        favourite = !favourite;
    }
    private void showDetails(){    }

    /**
     *  Updates Data in the json file (currently only favourite)
     */
    private void updateData() throws IOException {
        JSONObject item = (JSONObject) itemData.get(this.name);
        if((Boolean) item.get("favourite") != favourite){
            item.put("favourite", true);
            itemData.put(name, item);
            try(FileWriter file = new FileWriter("src/main/resources/itemData.json")){
                file.write(itemData.toString());
                file.close();
            }
        }
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
