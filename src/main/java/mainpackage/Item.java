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

    private int id;
    private String name;
    private float price;
    private float weight;
    private int available;
    private String description;
    private boolean favourite;

    public Item(int id, String name, float price, float weight, int available, String description, boolean favourite) throws IOException, ParseException {
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
