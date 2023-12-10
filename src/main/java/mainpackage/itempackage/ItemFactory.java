package mainpackage.itempackage;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ItemFactory {

    private static final Logger log = LogManager.getLogger(ItemFactory.class);

    /**
     * Creates item of a given type and id
     * @param type of item
     * @param id of item in json file
     * @return Item
     */
    public static Item createItem(ItemType type, int id){
        if(type == ItemType.GROCERY){
            log.debug("Item with id: " + id + " of type: " + type + " is being created");
            return new Grocery().createGrocery(id);
        }
        return null;
    }
}
