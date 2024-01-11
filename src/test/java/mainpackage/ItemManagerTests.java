package mainpackage;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import mainpackage.itempackage.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ItemManagerTests {

    private static final JsonReader jsonReader = JsonReader.getInstance();
    private static final Logger log = LogManager.getLogger(ItemManagerTests.class);

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
    public void testGetItempaneCategory() {
        ItemManager itemManager = ItemManager.getInstance();
        itemManager.loadNodes(15);

        FlowPane flowPane = itemManager.getItempaneCategory("bakery");

        List<Node> panes = flowPane.getChildren();
        assertEquals(3, panes.size());

        flowPane = itemManager.getItempaneCategory("sweets");

        panes = flowPane.getChildren();
        assertEquals(3, panes.size());

        FlowPane emptyFlowPane = itemManager.getItempaneCategory("NonExistingCategory");

        List<Node> emptyPanes = emptyFlowPane.getChildren();
        assertEquals(0, emptyPanes.size());
    }

    @Test
    public void testGetItempaneName() {
        ItemManager itemManager = ItemManager.getInstance();
        itemManager.loadNodes(15);

        FlowPane flowPane = itemManager.getItempaneName("ap");

        List<Node> panes = flowPane.getChildren();
        assertEquals(3, panes.size());

        flowPane = itemManager.getItempaneName("banana");

        panes = flowPane.getChildren();
        assertEquals(1, panes.size());

        FlowPane emptyFlowPane = itemManager.getItempaneName("NonExistingItem");

        List<Node> emptyPanes = emptyFlowPane.getChildren();
        assertEquals(1, emptyPanes.size());
        assertTrue(emptyPanes.get(0) instanceof Label);
    }
}
