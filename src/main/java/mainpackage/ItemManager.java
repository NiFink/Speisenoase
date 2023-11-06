package mainpackage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemManager {

    static List<Item> items = new ArrayList<>();
    static ObjectMapper objectMapper = new ObjectMapper();
    static File itemJsonFile = new File("src/main/resources/itemData.json");

    private static void createItemList(){
        try{
            items = objectMapper.readValue(itemJsonFile, new TypeReference<List<Item>>() {});
        } catch(IOException e){
            System.err.println(e.getMessage());
        }

    }

    private static void writeItemsToJson(){
        try{
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(itemJsonFile, items);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        createItemList();
        items.get(0).setFavourite(false);
        writeItemsToJson();
    }
}
