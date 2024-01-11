package mainpackage;

import javafx.application.Platform;
import mainpackage.itempackage.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemNodeTests {

    private static final JsonReader jsonReader = JsonReader.getInstance();
    private static final Logger log = LogManager.getLogger(ItemNodeTests.class);

    @BeforeAll
    public static void setUp() {
        Platform.startup(() -> {});
        jsonReader.start();
        try {
            jsonReader.join();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        User testUser = new User("test@test.com", "testUser", "testtest", new String[]{"Banana"});
        UserManager.getInstance().setActiveUser(testUser);
    }

    @Test
    public void testCreateItemNode() {
        Item item =  ItemFactory.createItem(ItemType.GROCERY, 0);
        ItemNode itemNode = new ItemNode(item);
        assertNotNull(itemNode.getItemPane());
        assertEquals(item.getName(), itemNode.getName());
        assertFalse(itemNode.isFavorite());
    }

    @Test
    public void testNonFavorite() {
        Item item = ItemFactory.createItem(ItemType.GROCERY, 0);
        ItemNode itemNode = new ItemNode(item);
        assertFalse(itemNode.isFavorite());
    }

    @Test
    public void testFavorite() {
        Item item = ItemFactory.createItem(ItemType.GROCERY, 1);
        ItemNode itemNode = new ItemNode(item);
        assertTrue(itemNode.isFavorite());
    }
}
