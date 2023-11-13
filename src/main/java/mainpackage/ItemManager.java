package mainpackage;
import mainpackage.itempackage.Item;
import mainpackage.itempackage.ItemFactory;
import mainpackage.itempackage.ItemType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private static Logger log = LogManager.getLogger(ItemManager.class);

    private static List<Item> getItems(){
        List<Item> items = new ArrayList<>();
        for(int i = 0; i < 15; i++){
            items.add(ItemFactory.createItem(ItemType.GROCERY, i));
        }
        log.info("List with " + items.size() + " items was created");
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
