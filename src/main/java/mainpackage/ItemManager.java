package mainpackage;
import mainpackage.itempackage.Item;
import mainpackage.itempackage.ItemFactory;
import mainpackage.itempackage.ItemType;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private static List<Item> getItems(){
        List<Item> items = new ArrayList<>();
        for(int i = 0; i < 15; i++){
            items.add(ItemFactory.createItem(ItemType.GROCERY, i));
        }
        return items;
    }

    /*private static void writeItemsToJson(){
        try{
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(itemJsonFile, groceries);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }*/

    public static void main(String[] args) {
        System.out.println(getItems().toString());
    }
}
