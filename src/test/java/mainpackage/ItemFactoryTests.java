package mainpackage;

import mainpackage.itempackage.Item;
import mainpackage.itempackage.ItemFactory;
import mainpackage.itempackage.ItemType;
import mainpackage.itempackage.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ItemFactoryTests {

    private final JsonReader jsonReader = JsonReader.getInstance();
    private static final Logger log = LogManager.getLogger(ItemFactory.class);
    @Test
    public void testCreateGroceryItem() {
        jsonReader.start();
        try {
            jsonReader.join();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        Item item = ItemFactory.createItem(ItemType.GROCERY, 1);
        assertNotNull(item);
        assertEquals("Banana", item.getName());
        assertEquals(0.4, item.getPrice());
        assertEquals("fruit", item.getCategory());
    }
}
